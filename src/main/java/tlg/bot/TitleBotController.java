package tlg.bot;

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

import java.util.List;
import java.util.stream.Collectors;

public class TitleBotController extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")){
            SendMessage message = StartService.getStartMessage(update);

            try{
                execute(message);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
        }

        else if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/registration")){
            SendMessage message = RegistrationService.getRegistrationMessage(update);

            try{
                execute(message);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
        }

        else if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/parse")) {
            SendMessage message = ParseService.getParseMessage(update);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        else if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/history")){

            SendMessage message = HistoryService.getHistoryMessage(update);
            try {
                execute(message);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }

        else if(update.hasMessage() && update.getMessage().hasText()){
            String msgText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage msg = new SendMessage() // Create a message object object
                    .setChatId(chatId)
                    .setText(msgText);
            try{
                execute(msg); // Sending our message object to user
            }
            catch (TelegramApiException e){
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
