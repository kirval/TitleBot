package telegram.bot.handler;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

public interface UpdateHandler {
    SendMessage handleUpdate(Update update);
}
