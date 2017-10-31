package tlg.bot;

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
import tlg.bot.services.HistoryService;
import tlg.bot.services.ParseService;
import tlg.bot.services.RegistrationService;
import tlg.bot.services.StartService;

import java.util.*;
import java.util.stream.Collectors;

public class TitleBotController extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId());

        if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")){
            StartService startService = new StartService(update);
            message = startService.getStartMessage();

            try{
                execute(message);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
        }

        else if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/registration")){
            RegistrationService registrationService = new RegistrationService(update);
            message = registrationService.getRegistrationMessage();

            try{
                execute(message);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
        }

        else if(RegistrationService.isRegistered(update.getMessage().getFrom())){

            if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/parse")){
                ParseService parseService = new ParseService(update);
                message = parseService.getParseMessage();

                try {
                    execute(message);
                }
                catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            else if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/history")){
                HistoryService historyService = new HistoryService(update);
                message = historyService.getHistoryMessage();

                try {
                    execute(message);
                }
                catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }

            else if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/pars1")){
                ParseService parseService = new ParseService(update);
                Document document = parseService.getDocumentFromUrl();
                Map<String, Elements> map = parseService.groupSimilarElements(document);
                Set<String> links = new HashSet<>();
                for (Map.Entry<String, Elements> entry : map.entrySet()) {
                    links.add(entry.getValue().get(0).attr("abs:href"));
                }
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getChatId()).setText(links.toString());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            else if(update.hasMessage() && update.getMessage().hasText()){
                message.setText("Используйте команды из списка /start");

                try {
                    execute(message);
                }
                catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
        }

        else {
            message.setText("Для использоваия бота зарегистрируйтесь!");

            try {
                execute(message);
            }
            catch (TelegramApiException e) {
                e.printStackTrace();
            }
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
