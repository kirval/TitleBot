package tlg.bot.services;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import tlg.bot.mapper.RequestMapper;
import tlg.bot.mapper.RequestMapperImpl;
import tlg.bot.models.Request;

import java.util.List;
import java.util.stream.Collectors;

public class HistoryService {

    private Update update;
    private RequestMapper requestMapper;

    public void setUpdate(Update update) {
        this.update = update;
    }

    public void setRequestMapper() {
        this.requestMapper = new RequestMapperImpl();
    }

    public HistoryService(Update update){
        setUpdate(update);
        setRequestMapper();
    }

    public  SendMessage getHistoryMessage(){
        Integer telegramID = update.getMessage().getFrom().getId();
        List<Request> history = requestMapper.selectTop10RequestsByAccountId(telegramID);
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
