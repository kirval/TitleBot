package tlg.bot.services;


import org.apache.ibatis.exceptions.PersistenceException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import tlg.bot.mapper.AccountMapperImpl;
import tlg.bot.models.Account;

public class RegistrationService {

    private Update update;
    private User user;

    public void setUpdate(Update update) {
        this.update = update;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RegistrationService(Update update){
        setUpdate(update);
        setUser(update.getMessage().getFrom());
    }

    public  SendMessage getRegistrationMessage() {

        long chatId = update.getMessage().getChatId();
        SendMessage message = new SendMessage().setChatId(chatId);

        try{
            saveAccount();

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

    public void saveAccount() {
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