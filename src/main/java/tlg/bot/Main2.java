package tlg.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Stream;

public class Main2 {

    public static void main(String[] args) {
        String url = "https://www.kufar.by/%D0%B1%D0%B5%D0%BB%D0%B0%D1%80%D1%83%D1%81%D1%8C/%D0%90%D0%B2%D1%82%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D0%B8-%D0%BF%D1%80%D0%BE%D0%B4%D0%B0%D0%B5%D1%82%D1%81%D1%8F";  //"https://www.ebay.com/sch/Cell-Phones-Smartphones-/9355/i.html";


        Document document = getDocumentFromUrl(url);

        System.out.println(document.select("a[href]").size());
/*
        document.getElementsByTag("header").remove();
        document.getElementsByTag("footer").remove();

        Elements elements = document.body().getAllElements();
        Elements links = elements.select("a[href]");
        removeUselessLinks(links);*/

       // Map<Element, Elements> map = groupByFirstSameParent(links);

      //  System.out.println(map.size());
    }

    private static Element findKeyOfTheBiggestGroup(Map<Element, Elements> map) {
        int maxSize = Integer.MIN_VALUE;
        Element element = null;
        for(Map.Entry<Element, Elements> entry: map.entrySet()) {
            if(entry.getValue().size() > maxSize) {
                maxSize = entry.getValue().size();
                element = entry.getKey();
            }
        }
        return element;
    }

    public static void removeUselessLinks(Elements elements) {

        elements.removeIf(a ->
                a.select("img").isEmpty() || a.attr("abs:href").endsWith(".img") || a.attr("abs:href").endsWith(".jpg") || a.attr("abs:href").isEmpty()
                        || a.attr("abs:href").endsWith(".css") || a.attr("href").startsWith("#") || elements.select("a[href=\"" + a.attr("abs:href") + "\"]").size() <= 1);
        elements.removeIf(a -> elements.select("a[href=\"" + a.attr("abs:href") + "\"]").size() > 1);
        //elements.stream().filter(a -> a.getElementsByAttribute("abs:href").size() > 1);
        System.out.println(elements.size());


        elements.forEach(a -> System.out.println(a.attr("href")));
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

/*    public static Map<Element, Elements> groupByFirstSameParent(Elements elements) {
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
    }*/

    public static Boolean haveSameParent(Elements parents1, Elements parents2) {
        for (int i = 0; i < parents1.size() && i < parents2.size(); i++) {
            if(parents1.get(i) == parents2.get(i)){
                return true;
            }
        }
        return false;
    }

    public static Element getSameParent(Elements parents1, Elements parents2) {
        for (int i = 0; i < parents1.size() && i < parents2.size(); i++) {
            if(parents1.get(i) == parents2.get(i)){
                return parents1.get(i);
            }
        }
        return null;
    }

}
