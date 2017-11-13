package tlg.bot.services;


import org.apache.ibatis.exceptions.PersistenceException;
import org.telegram.telegrambots.api.objects.User;
import tlg.bot.mapper.AccountMapper;
import tlg.bot.mapper.AccountMapperImpl;
import tlg.bot.models.Account;

public class RegistrationService {
    AccountMapper accountMapper = new AccountMapperImpl();

    public String register(User user) {
        String msgText;

        try{
            saveAccount(user);

            msgText = "Вы успешно зарегестрировались!" +
                    "\nВаш ID: " + user.getId() +
                    "\nВаше имя: " + user.getFirstName() +
                    "\nВаша фамилия: " + user.getLastName() +
                    "\nВаш никнейм: " + user.getUserName();
        }
        catch (PersistenceException e){
            e.printStackTrace();
            msgText = "Ошибка регистрации. Такой пользователь уже существует!";
        }
        return msgText;
    }

    private void saveAccount(User user) {
        Account account = new Account(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName());

        accountMapper.insertAccount(account);
    }

    public boolean isRegistered(User user){
        boolean isRegistered = true;
        Integer telegramID = user.getId();
        Account account = accountMapper.findByTelegramID(telegramID);

        try{
            account.getTelegramId();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            isRegistered = false;
        }
        return isRegistered;
    }


    // ДОБАВИТЬ ЗАГЛУШКИ НА ОПЦИОНАЛЬНЫЕ ПОЛЯ USER

}