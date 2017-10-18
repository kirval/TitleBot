package tlg.bot.services;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class StartService {

    public static SendMessage getStartMessage(Update update){
        String msgText = "TitleBot успешно запущен!" +
                "\nДля использования бота введите команду /registration";
        long chatId = update.getMessage().getChatId();

        SendMessage message = new SendMessage()
                .setText(msgText)
                .setChatId(chatId);
        return message;
    }
}
