package tlg.bot.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import tlg.bot.mapper.RequestMapperImpl;
import tlg.bot.models.Request;

public class ParseService {

    public static SendMessage getParseMessage(Update update){
        String messageText = update.getMessage().getText();
        String url = messageText.replace("/parse ", "");
        String title = getTitleFromDocument(url);
        String incorrectUrl = "Неверный адрес. Попробуйте другой";
        long chatId = update.getMessage().getChatId();

        SendMessage message = new SendMessage().setChatId(chatId);

        if(title != null){
            message.setText(title);
            Request request = new Request(update.getMessage().getFrom().getId(), url, title);
            saveRequest(request);
        }
        else message.setText(incorrectUrl);

        return message;
    }

    public static Document getDocumentFromUrl(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    public static String getTitleFromDocument(String url) {
        Document doc = getDocumentFromUrl(url);
        if(doc != null) {
            String title = doc.title();
            return title;
        }
        else return null;
    }



    public static void saveRequest(Request request){
        RequestMapperImpl requestMapper = new RequestMapperImpl();
        requestMapper.insertRequest(request);
    }

}
