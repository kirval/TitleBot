package tlg.bot.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParserService {

    public String getHtmlFromurl(String theUrl) {
        return getDocumentFromUrl(theUrl).toString();
    }

    private Document getDocumentFromUrl(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }

    public String getTitleFromUrl(String url) {
        Document doc = getDocumentFromUrl(url);
        if(doc != null) {
            String title = doc.title();
            return title;
        }
        else return null;
    }
}
