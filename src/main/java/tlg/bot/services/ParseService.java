package tlg.bot.services;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import tlg.bot.models.Request;
import tlg.bot.mapper.RequestMapperImpl;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        setUrl(update.getMessage().getText().replace("/pars1 ", ""));
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


    public Map<String, Element> groupSimilarElements1(Document document) {
        Elements elements = document.body().getAllElements();
        HashMap<String, Element> map = new HashMap<>();
        Elements divs = elements.select("div");
        if(!elements.isEmpty()){
            for(Element element: divs) {
                Elements links = element.select("a[href]"); //
                if(!links.isEmpty()){
                    map.put(links.attr("abs:href"), element);
                    System.out.println(links.attr("abs:href"));
                }
            }
        }

        return map;
    }

    public Map<String, Elements> groupSimilarElements(Document document) {
        Elements elements = document.body().getAllElements();
        Elements links = elements.select("a[href]");
        HashMap<String, Elements> map = new HashMap<>();
        if(!links.isEmpty()){
            for(Element element: links) {
                String className = element.className();
                    if(map.containsKey(className)){
                        map.get(className).add(element);
                    }
                    else {
                        Elements list = new Elements();
                        list.add(element);
                        map.put(className, list);
                        System.out.println(className);
                    }
                }
            }
            System.out.println(map.size());

        return map;
    }

    public boolean isValidUrl(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}

