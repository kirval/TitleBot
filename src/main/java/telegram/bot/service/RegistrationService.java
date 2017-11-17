package telegram.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.User;
import telegram.bot.domain.Account;
import telegram.bot.mapper.AccountMapper;

@Service
public class RegistrationService {

    @Autowired
    private AccountMapper accountMapper;

    private User telegramUser;

    public void setTelegramUser(User telegramUser) {
        this.telegramUser = telegramUser;
    }

    public String getRegistrationMessage(){
        String messageText;

        if(registerUser()){
            messageText = "Вы успешно зарегестрировались!" +
                    "\nВаш ID: " + telegramUser.getId() +
                    "\nВаше имя: " + telegramUser.getFirstName() +
                    "\nВаша фамилия: " + telegramUser.getLastName();

            if(telegramUser.getUserName()!=null)
                messageText = messageText.concat("\nВаш никнейм: " + telegramUser.getUserName());
        }
        else messageText = "Ошибка регистрации. Такой пользователь уже существует.";

        return messageText;
    }

    private boolean registerUser() {
        boolean isRegisterSuccessfully = true;
        Account account = new Account(telegramUser.getId(), telegramUser.getFirstName(), telegramUser.getLastName(), telegramUser.getUserName());

        try{
        accountMapper.insertAccount(account);
        }
        catch (DuplicateKeyException e){
            isRegisterSuccessfully = false;
        }

        return isRegisterSuccessfully;
    }

    public boolean isRegistered(User user){
        boolean isRegistered = false;

        Account account = new Account(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName());

        try{
            accountMapper.insertAccount(account);
        }
        catch (DuplicateKeyException e){
            isRegistered = true;
        }

        return isRegistered;
    }
}