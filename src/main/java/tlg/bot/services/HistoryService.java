package tlg.bot.services;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import tlg.bot.mapper.AccountMapper;
import tlg.bot.mapper.AccountMapperImpl;
import tlg.bot.mapper.RequestMapper;
import tlg.bot.mapper.RequestMapperImpl;
import tlg.bot.models.Request;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryService {

    public static SendMessage getHistoryMessage(Update update){
        RequestMapper requestMapper = new RequestMapperImpl();
        Integer id = update.getMessage().getFrom().getId();
        List<Request> history = requestMapper.selectTop10RequestsByAccountId(id);
        long chatId = update.getMessage().getChatId();


        String h = history.stream().map (i -> i.toString ()).collect(Collectors.joining ("\n"));

        SendMessage message = new SendMessage().setChatId(chatId);
        message.setText(h);

        if(message.getText().equals("")){
            message.setText("Ваша история запросов пуста.");
        }

        return message;
    }
}
