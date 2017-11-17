package telegram.bot.service;

import org.springframework.stereotype.Service;

@Service
public class StartService {

    public String getStartMessage(){
        return "Для использования команд бота зарегистрируйтесь /registration" +
                "\n-- Для получения списка товаров со страницы используйте /orders <ссылка на страницу>" +
                "\n-- Для получения названия страницы используйте /parse_title <ссылка на страницу>" +
                "\n-- Для просмотра 10 последних запросов используйте /history";
    }
}
