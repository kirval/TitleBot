package tlg.bot.services;

import lombok.Data;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParseService2 {

    public Map<Element, Elements> getOrdersGroup(String url){
        if (!isValidUrl(url)){
            System.out.println("INVALID URL:" + url);
            return null;
        }

        Document document = getDocumentFromUrl(url);
        document.getElementsByTag("header").remove();
        document.getElementsByTag("footer").remove();

        Elements elements = document.body().getAllElements();
        Elements links = elements.select("a[href]");
       // Elements links2 =  document.getElementsByAttribute("abs:href");

        removeUselessLinks(links);
        Map<Element, Elements> map = groupByFirstSameParent(links);

        return map;
    }

    public Elements getOrders(String url, String elementId) {
        if (!isValidUrl(url)){
            System.out.println("INVALID URL:" + url);
            return null;
        }

        Document document = getDocumentFromUrl(url);
        Elements elements = document.getElementById(elementId).select("a[href]");
        removeUselessLinks(elements);

        return elements;
    }

    public String getOrdersGroupAsString(String url) {
        Map<Element, Elements> orders = getOrdersGroup(url);
        StringBuilder orderLinksBuilder = new StringBuilder();

        for (Map.Entry<Element, Elements> entry: orders.entrySet()) {

            System.out.println(entry.getValue().size());
            entry.getValue().forEach(a -> orderLinksBuilder.append("\n").append(a.attr("abs:href")));
        }

        return orderLinksBuilder.toString();
    }

    public String getTitle(String url) {
        if(isValidUrl(url)) {
            Document document = getDocumentFromUrl(url);
            if(document != null) {
                String title = document.title();
                return title;
            } else return null;
        }
        else return null;
    }

    public Boolean isValidUrl(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }

    private void removeUselessLinks(Elements elements) {
        System.out.println(elements.size());
        elements.forEach( a -> {
            String attr = a.attr("abs:href");
            if(!attr.isEmpty()) {
                a.attr(attr, removeQueryString(attr));
            }
        });

        elements.removeIf(a ->
                a.select("img").isEmpty()   || a.attr("abs:href").isEmpty()
                          || elements.select("a[href=\"" + a.attr("abs:href") + "\"]").size() <= 1);
    }

    private String removeQueryString(String theURL) {
        int endPos;
        if (theURL.indexOf("?") > 0) {
            endPos = theURL.indexOf("?");
        } else if (theURL.indexOf("#") > 0) {
            endPos = theURL.indexOf("#");
        } else {
            endPos = theURL.length();
        }

        return theURL.substring(0, endPos);
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

    private Map<Element, Elements> groupByFirstSameParent(Elements elements) {
        Map<Element, Elements> map = new HashMap<>();
        for (Element element1: elements) {
            for (Element element2: elements){
                Elements parents1 = element1.parents();
                Elements parents2 = element2.parents();
                if(haveSameParent(parents1, parents2) && !element1.equals(element2)){
                    Element sameParent = getSameParent(parents1, parents2);
                    Elements bElements = new Elements();

                    if(map.containsKey(sameParent)){
                        bElements = map.get(sameParent);
                        if(!bElements.contains(element1) && !bElements.contains(element2)){
                            bElements.add(element1);
                            bElements.add(element2);
                        } else if(!bElements.contains(element1)){
                            bElements.add(element1);
                        } else if(!bElements.contains(element2)) {
                            bElements.add(element2);
                        }
                    } else {
                        bElements.add(element1);
                        bElements.add(element2);
                        map.put(sameParent, bElements);
                    }
                }
            }
        }
        return map;
    }

    private Boolean haveSameParent(Elements parents1, Elements parents2) {
        for (int i = 0; i < parents1.size() && i < parents2.size(); i++) {
            if(parents1.get(i) == parents2.get(i)){
                return true;
            }
        }
        return false;
    }

    private Element getSameParent(Elements parents1, Elements parents2) {
        for (int i = 0; i < parents1.size() && i < parents2.size(); i++) {
            if(parents1.get(i) == parents2.get(i)){
                return parents1.get(i);
            }
        }
        return null;
    }
}
