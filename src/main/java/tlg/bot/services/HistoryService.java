package tlg.bot.services;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import tlg.bot.mapper.AccountMapper;
import tlg.bot.mapper.AccountMapperImpl;
import tlg.bot.models.Request;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryService {

    public static SendMessage getHistoryMessage(Update update){
        AccountMapper accountMapper = new AccountMapperImpl();
        Integer id = update.getMessage().getFrom().getId();
        List<Request> history = accountMapper.selectTop10RequestsByAccountId(id);
        long chatId = update.getMessage().getChatId();


        String h = history.stream().map (i -> i.toString ()).collect(Collectors.joining ("\n"));

        SendMessage message = new SendMessage().setChatId(chatId);
        message.setText(h);

        return message;
    }
}
