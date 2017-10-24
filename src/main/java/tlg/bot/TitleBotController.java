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

        SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId());

        if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")){
            message = StartService.getStartMessage(update);

            try{
                execute(message);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
        }

        else if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/registration")){
            message = RegistrationService.getRegistrationMessage(update);

            try{
                execute(message);
            }
            catch (TelegramApiException e){
                e.printStackTrace();
            }
        }

        else if(RegistrationService.isRegistered(update.getMessage().getFrom())){

            if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/parse")){
                message = ParseService.getParseMessage(update);
                try {
                    execute(message);
                }
                catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            else if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/history")){
                message = HistoryService.getHistoryMessage(update);
                try {
                    execute(message);
                }
                catch (TelegramApiException e){
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
        return "463056223:AAExTo9qMPlLoX_BBWGwXrxhqX8uBbeQ6BI";
    }
}
