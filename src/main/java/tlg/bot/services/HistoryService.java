package tlg.bot.services;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import tlg.bot.mapper.RequestMapper;
import tlg.bot.mapper.RequestMapperImpl;
import tlg.bot.models.Request;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryService {

    RequestMapper requestMapper = new RequestMapperImpl();

    public  String getHistoryMessage(Integer telegramId){
        List<Request> history = requestMapper.selectTop10RequestsByAccountId(telegramId);
        String h = history.stream().map (i -> i.toString ()).collect(Collectors.joining ("\n"));

        return h;
    }
}
