package tlg.bot;

import com.google.inject.Inject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import tlg.bot.mapper.AccountMapper;
import tlg.bot.mapper.AccountMapperImpl;
import tlg.bot.models.Request;
import tlg.bot.services.*;

import java.util.*;
import java.util.stream.Collectors;

public class TitleBotController extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId());

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            StartService startService = new StartService(update);
            message = startService.getStartMessage();

            executeMessage(message);
        } else if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/registration")) {
            RegistrationService registrationService = new RegistrationService(update);
            message = registrationService.getRegistrationMessage();

            executeMessage(message);
        } else if (RegistrationService.isRegistered(update.getMessage().getFrom())) {

            if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/parse")) {
                ParseService parseService = new ParseService(update);
                message = parseService.getParseMessage();

                executeMessage(message);
            } else if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/history")) {
                HistoryService historyService = new HistoryService(update);
                message = historyService.getHistoryMessage();

                executeMessage(message);
            } else if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/orders")) {
                ParseService2 parseService2 = new ParseService2();
                String url = update.getMessage().getText().replace("/orders ", "");
                Map<Element, Elements> orders = parseService2.getOrdersGroup(url);
                SendMessage sendMessage = new SendMessage();


                for (Map.Entry<Element, Elements> entry: orders.entrySet()) {
                    StringBuilder orderLinksBuilder = new StringBuilder();
                    System.out.println(entry.getValue().size());
                    entry.getValue().forEach(a -> orderLinksBuilder.append("\n").append(a.attr("abs:href")));
                    sendMessage.setChatId(update.getMessage().getChatId()).setText(orderLinksBuilder.toString());
                    executeMessage(sendMessage);
                }


            } else if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/pars1")) {
                ParseService parseService = new ParseService(update);
                Document document = parseService.getDocumentFromUrl();
                Map<String, Elements> map = parseService.groupSimilarElements(document);
                Set<String> links = new HashSet<>();
                for (Map.Entry<String, Elements> entry : map.entrySet()) {
                    links.add(entry.getValue().get(0).attr("abs:href"));
                }
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId()).setText(links.toString());

                executeMessage(message);
            } else if (update.hasMessage() && update.getMessage().hasText()) {
                message.setText("Используйте команды из списка /start");

                executeMessage(message);
            }
        } else {
            message.setText("Для использоваия бота зарегистрируйтесь!");

            executeMessage(message);
        }
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "vkir_title_bot";
    }


    @Override
    public String getBotToken() {
        return "461612025:AAGHeK9ceer_fe4hk2FdRtcIu4YASVOEjwk";
    }
}
