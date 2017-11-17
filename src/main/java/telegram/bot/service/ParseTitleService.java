package telegram.bot.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.objects.User;
import telegram.bot.domain.TitleRequest;
import telegram.bot.mapper.TitleRequestMapper;


@Service
public class ParseTitleService {

    @Autowired
    TitleRequestMapper titleRequestMapper;

    private User telegramUser;

    public void setTelegramUser(User telegramUser) {
        this.telegramUser = telegramUser;
    }

    public String getParseTitleMessage(String command) {

        String incorrectUrl = "Неверный адрес. Попробуйте другой.";
        String url = command.replace("/parse_title ", "");
        String title = getTitleFromDocument(url);

        if(title != null){
            TitleRequest titleRequest = new TitleRequest(telegramUser.getId(), url, title);
            saveRequest(titleRequest);
            return title;
        }
        else return incorrectUrl;
    }

    private String getTitleFromDocument(String url)  {
        Document doc = getDocumentFromUrl(url);
        if(doc != null) {
            String title = doc.title();
            return title;
        }
        else return null;
    }

    private Document getDocumentFromUrl(String url){
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        }
        catch (Exception e){
            doc = null;
            e.printStackTrace();
        }

        return doc;
    }

    private void saveRequest(TitleRequest titleRequest){
        titleRequestMapper.insertRequest(titleRequest);
    }
}

