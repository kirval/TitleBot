package tlg.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import tlg.bot.services.HtmlParserService;

public class TitleBot extends TelegramLongPollingBot {

    private HtmlParserService htmlParserService = new HtmlParserService();

    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")){
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

        else if(update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().startsWith("/parse")) {
            String msgText = update.getMessage().getText();
            String url = msgText.replace("/parse ", "");
            long chatId = update.getMessage().getChatId();
            String title = htmlParserService.getTitleFromUrl(url);
            String incrorrectUrl = "Incorrect url. Try again";
            SendMessage msg;

            if(title != null) {
                msg = new SendMessage()
                        .setChatId(chatId)
                        .setText(title);
            } else {
                msg = new SendMessage()
                        .setChatId(chatId)
                        .setText(incrorrectUrl);
            }
            try {
                execute(msg);
            } catch (TelegramApiException e) {
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
