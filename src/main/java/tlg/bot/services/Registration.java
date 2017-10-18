package tlg.bot.services;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;

public class Registration {

    public static SendMessage getRegistrationMessage(Update update) {

        User user = update.getMessage().getFrom();

        String msgText = "Вы успешно зарегестрировались!" +
                "\nВаш ID: " + user.getId() +
                "\nВаше имя: " + user.getFirstName() +
                "\nВаша фамилия: " + user.getLastName() +
                "\nВаш никнейм: " + user.getUserName();
        long chatId = update.getMessage().getChatId();

        SendMessage message = new SendMessage()
                .setText(msgText)
                .setChatId(chatId);
        return message;
    }

    // ДОБАВИТЬ СОХРАНЕНИЕ В БД
    // ДОБАВИТЬ ЗАГЛУШКИ НА ОПЦИОНАЛЬНЫЕ ПОЛЯ USER
    
}
