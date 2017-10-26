package tlg.bot.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import tlg.bot.models.Request;
import tlg.bot.mapper.RequestMapperImpl;

public class ParseService {

    private Update update;
    private String url;
    private String title;
    private static final String incorrectUrl = "Неверный адрес. Попробуйте другой.";

    public void setUpdate(Update update) {
        this.update = update;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ParseService(Update update){
        setUpdate(update);
        setUrl(update.getMessage().getText().replace("/parse ", ""));
        setTitle(null);
    }

    public SendMessage getParseMessage(){
        title = getTitleFromDocument();
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

    public String getTitleFromDocument() {
        Document doc = getDocumentFromUrl();
        if(doc != null) {
            String title = doc.title();
            return title;
        }
        else return null;
    }

    public Document getDocumentFromUrl() {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    public void saveRequest(Request request){
        RequestMapperImpl requestMapper = new RequestMapperImpl();
        requestMapper.insertRequest(request);
    }
}
