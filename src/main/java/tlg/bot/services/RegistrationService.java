package tlg.bot.services;


import org.apache.ibatis.exceptions.PersistenceException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import tlg.bot.mapper.AccountMapperImpl;
import tlg.bot.models.Account;

public class RegistrationService {

    public static SendMessage getRegistrationMessage(Update update) {

        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage().setChatId(chatId);
        User user = update.getMessage().getFrom();

        try{
            saveAccount(user);

            String msgText = "Вы успешно зарегестрировались!" +
                    "\nВаш ID: " + user.getId() +
                    "\nВаше имя: " + user.getFirstName() +
                    "\nВаша фамилия: " + user.getLastName() +
                    "\nВаш никнейм: " + user.getUserName();
            message.setText(msgText);
        }
        catch (PersistenceException e){
            e.printStackTrace();
            message.setText("Ошибка регистрации. Такой пользователь уже существует!");
        }
        return message;
    }

    public static void saveAccount(User user) {
        Account account = new Account(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName());
        AccountMapperImpl accountMapper = new AccountMapperImpl();

        accountMapper.insertAccount(account);
    }

    public static boolean isRegistered(User user){
        boolean isRegistered = true;
        Integer telegramID = user.getId();
        AccountMapperImpl accountMapper = new AccountMapperImpl();
        Account account = accountMapper.findByTelegramID(telegramID);

        try{
            account.getTelegramID();

        }
        catch (NullPointerException e){
            e.printStackTrace();
            isRegistered = false;
        }
        return isRegistered;
    }

    // ДОБАВИТЬ ЗАГЛУШКИ НА ОПЦИОНАЛЬНЫЕ ПОЛЯ USER

}