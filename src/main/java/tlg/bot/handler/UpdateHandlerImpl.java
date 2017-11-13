package tlg.bot.handler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
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
                String query = text.substring(index);
                //Refactor
                Map<Element, Elements> orders = parseService2.getOrdersGroup(query);

                for (Map.Entry<Element, Elements> entry : orders.entrySet()) {
                    Selector selector = new Selector(update.getMessage().getChatId(), query);
                    selectorMapper.insertSelector(selector);

                    entry.getValue().forEach(a -> orderMapper.insertOrder(new Order(a.attr("abs:href"), selector.getId())));


                    StringBuilder orderLinksBuilder = new StringBuilder();
                    System.out.println(entry.getValue().size());
                    entry.getValue().forEach(a -> orderLinksBuilder.append("\n").append(a.attr("abs:href")));
                    sendMessage.setText(orderLinksBuilder.toString());
                    return sendMessage;
                }
            } else if (text.startsWith("/parse")) {
                String query = text.substring(index);
                String title = parseService2.getTitle(query);
                sendMessage.setText(text);
            } else {
                sendMessage.setText("Используйте команды из списка /start");
            }
        } else {
            sendMessage.setText("Для использоваия бота зарегистрируйтесь!");
        }

        return sendMessage;
    }

}
