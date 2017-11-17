package telegram.bot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telegram.bot.domain.TitleRequest;
import telegram.bot.mapper.TitleRequestMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    @Autowired
    TitleRequestMapper titleRequestMapper;

    public  String getHistoryMessage(Integer telegramId){
        List<TitleRequest> history = titleRequestMapper.selectLast10RequestsByTelegramId(telegramId);
        String messageText = history.stream().map (i -> i.toString ()).collect(Collectors.joining ("\n\n"));

        if(messageText.isEmpty()) messageText = "Ваша история пуста.";

        return messageText;
    }
}
