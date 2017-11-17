package telegram.bot.handler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import telegram.bot.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class UpdateHandlerImpl implements UpdateHandler{

    @Autowired
    private StartService startService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ParseTitleService parseTitleService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ParseOrdersService parseOrdersService;

    @Override
    public SendMessage handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChat().getId());

            if (update.getMessage().getText().equals("/start")) {
                message.setText(startService.getStartMessage());
            }
            else if(update.getMessage().getText().equals("/test")){
                message.setText("http://minsk.irr.by/realestate/new/2-komn-kvartira-Schorsa-ul-10-14-ploschad-obschaya-advert9892565.html");

                InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
                InlineKeyboardButton button = new InlineKeyboardButton().setText("button").setCallbackData("update_msg_text");
                List<List<InlineKeyboardButton>> allButtonLine = new ArrayList<>();
                List<InlineKeyboardButton> buttonLine = new ArrayList<>();

                // Set the buttons to the keyboard
                buttonLine.add(button);
                allButtonLine.add(buttonLine);

                // Add keyboard to the message
                keyboard.setKeyboard(allButtonLine);
                message.setReplyMarkup(keyboard);
            }
            else if (update.getMessage().getText().equals("/registration")){
                registrationService.setTelegramUser(update.getMessage().getFrom());
                message.setText(registrationService.getRegistrationMessage());
            }
            else if (registrationService.isRegistered(update.getMessage().getFrom())){
                if(update.getMessage().getText().startsWith("/parse_title")){
                    parseTitleService.setTelegramUser(update.getMessage().getFrom());
                    message.setText(parseTitleService.getParseTitleMessage(update.getMessage().getText()));
                }
                else if(update.getMessage().getText().equals("/history")){
                    message.setText(historyService.getHistoryMessage(update.getMessage().getFrom().getId()));
                }
                else if (update.getMessage().getText().startsWith("/orders")) {
                    String url = update.getMessage().getText().replace("/orders ", "");

                    Map<Element, Elements> orders = parseOrdersService.getOrdersGroup(url);

                    for (Map.Entry<Element, Elements> entry : orders.entrySet()) {
                        StringBuilder orderLinksBuilder = new StringBuilder();
                        System.out.println(entry.getValue().size());
                        entry.getValue().forEach(a -> orderLinksBuilder.append("\n\n").append(a.attr("abs:href")));
                        message.setText(orderLinksBuilder.toString());
                    }


                }
                else message.setText("Используйте команды из списка /start");
            }
            else message.setText("Для использования бота зарегистрируйтесь!");

            return message;
        }
        return null;
    }


}
