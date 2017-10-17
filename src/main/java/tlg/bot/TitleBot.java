package tlg.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TitleBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText()=="/start"){
            String msgText = "TitleBot successfully started!";
            long chatId = update.getMessage().getChatId();

            SendMessage msg = new SendMessage()
                    .setText(msgText)
                    .setChatId(chatId);

            try{
                execute(msg);
            }
            catch (TelegramApiException e){
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
        return "463056223:AAExTo9qMPlLoX_BBWGwXrxhqX8uBbeQ6BI";
    }
}
