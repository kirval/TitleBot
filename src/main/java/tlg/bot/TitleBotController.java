package tlg.bot;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import tlg.bot.handler.UpdateHandler;
import tlg.bot.handler.UpdateHandlerImpl;
import tlg.bot.mapper.OrderMapperImpl;
import tlg.bot.mapper.SelectorMapperImpl;
import tlg.bot.models.Order;
import tlg.bot.models.Selector;
import tlg.bot.services.*;

import java.util.*;

public class TitleBotController extends TelegramLongPollingBot {
    UpdateHandler updateHandler = new UpdateHandlerImpl();

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage message = updateHandler.handleUpdate(update);
        executeMessage(message);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "vkir_title_bot";
    }


    @Override
    public String getBotToken() {
        return "461612025:AAGHeK9ceer_fe4hk2FdRtcIu4YASVOEjwk";
    }
}
