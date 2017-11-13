package tlg.bot.services;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public class StartService {

    public String getStartMessage(){
        String msgText = "Вас приветствует TitleBot!" +
                "\n-- Для использования команд бота зарегистрируйтесь /registration" +
                "\n-- Для получения названия страницы используйте /parse <ссылка на страницу>" +
                "\n-- Для просмотра 10 последних запросов используйте /history";

        return msgText;
    }


}
