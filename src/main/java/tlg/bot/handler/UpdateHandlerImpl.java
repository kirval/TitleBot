package tlg.bot.handler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import tlg.bot.mapper.OrderMapper;
import tlg.bot.mapper.OrderMapperImpl;
import tlg.bot.mapper.SelectorMapper;
import tlg.bot.mapper.SelectorMapperImpl;
import tlg.bot.models.Order;
import tlg.bot.models.Selector;
import tlg.bot.services.HistoryService;
import tlg.bot.services.ParseService2;
import tlg.bot.services.RegistrationService;
import tlg.bot.services.StartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UpdateHandlerImpl implements UpdateHandler{

    private ParseService2 parseService2 = new ParseService2();
    private HistoryService historyService = new HistoryService();
    private StartService startService = new StartService();
    private RegistrationService registrationService = new RegistrationService();
    private SelectorMapper selectorMapper = new SelectorMapperImpl();
    private OrderMapper orderMapper = new OrderMapperImpl();

    @Override
    public SendMessage handleUpdate(Update update) {

        Map<Element, Elements> orders = null;
        String query = "";

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = message.getChat().getId();
            String text = message.getText();
            User user = message.getFrom();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);

            int index = text.indexOf(" ");

            if (text.startsWith("/start")) {
                String startMessage = startService.getStartMessage();
                sendMessage.setText(startMessage);
            } else if (text.startsWith("/registration")) {
                String registrationMessage = registrationService.register(user);
                sendMessage.setText(registrationMessage);
            } else if (registrationService.isRegistered(user)) {
                if (text.startsWith("/history")) {
                    String historyMessage = historyService.getHistoryMessage(user.getId());
                    sendMessage.setText(historyMessage);
                } else if (text.startsWith("/orders")) {
                    query = text.substring(index + 1);

                    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();

                    orders = parseService2.getOrdersGroup(query);
                    sendMessage.setText("Choose pls correct link");

                    for (Map.Entry<Element, Elements> entry : orders.entrySet()) {
                        rowInline.add(new InlineKeyboardButton()
                                .setText(entry.getValue().first().attr("abs:href"))
                                .setCallbackData(String.valueOf(entry.getKey().hashCode())));
                        rowsInline.add(rowInline);
                    }
                    keyboardMarkup.setKeyboard(rowsInline);
                    sendMessage.setReplyMarkup(keyboardMarkup);
                } else if (text.startsWith("/parse")) {
                    query = text.substring(index + 1);
                    String title = parseService2.getTitle(query);
                    sendMessage.setText(title);
                } else {
                    sendMessage.setText("Используйте команды из списка /start");
                }
            } else {
                sendMessage.setText("Для использоваия бота зарегистрируйтесь!");
            }

            return sendMessage;
        } else if(update.hasCallbackQuery()){
            String callData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            for (Map.Entry<Element, Elements> entry: orders.entrySet()) {
                if(callData.equals(String.valueOf(entry.getKey().hashCode()))) {
                    String answer = "Следим за новыми товарами";
                    Selector selector = new Selector(update.getMessage().getChatId(), query);
                    selectorMapper.insertSelector(selector);
                    entry.getValue().forEach(a -> orderMapper.insertOrder(new Order(a.attr("abs:href"), selector.getId())));

                    SendMessage sendMessage = new SendMessage().setChatId(chatId).setText(answer);
                    return sendMessage;
                }
            }
        }

        return null;
    }


}
