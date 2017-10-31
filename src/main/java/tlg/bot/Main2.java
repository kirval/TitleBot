package tlg.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class Main2 {

    public static void main(String[] args) {
        String url = "https://www.ebay.com/sch/Cell-Phones-Smartphones-/9355/i.html";


        Document document = getDocumentFromUrl(url);
        Elements elements = document.body().getAllElements();
        Elements links = elements.select("a[href]");
        removeImageLinks(elements);

        Map<Element, Elements> map = groupByFirstSameParent(links);
        //System.out.println(map.size());
/*
        for (Map.Entry<Element, Elements> entry: map.entrySet()){
            System.out.println(entry.getKey().html());
        }*/

/*        Map<String, Elements> map = groupSimilarElements(document);
        Set<String> links = new HashSet<>();
        for (Map.Entry<String, Elements> entry : map.entrySet()) {
            links.add(entry.getValue().get(0).attr("abs:href"));
        }*/

        //System.out.println(links.toString());
    }

    public static void removeImageLinks(Elements elements) {
        elements.removeIf(a -> a.attr("abs:href").endsWith(".img") || a.attr("abs:href").endsWith(".jpg") || a.attr("abs:href").isEmpty() || a.attr("abs:href").endsWith(".css"));
        System.out.println(elements.size());
        elements.forEach(a -> System.out.println(a.attr("abs:href")));
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

    public static Map<String, Elements> groupSimilarElements(Document document) {
        Elements elements = document.body().getAllElements();
        Elements links = elements.select("a[href]");
        HashMap<String, Elements> map = new HashMap<>();
        if(!links.isEmpty()){
            for(Element element: links) {
                Elements parents = element.parents();
                String className = element.className();
                if(map.containsKey(className)){
                    map.get(className).add(element);
                }
                else {
                    Elements list = new Elements();
                    list.add(element);
                    map.put(className, list);
                    //System.out.println(className);
                }
            }
        }
        System.out.println(map.size());

        return map;
    }

    public static Map<Element, Elements> groupByFirstSameParent(Elements elements) {
        Map<Element, Elements> map = new HashMap<>();
        for (Element element1: elements) {
            for (Element element2: elements){
                Elements parents1 = element1.parents();
                Elements parents2 = element2.parents();
                if(haveSameParent(parents1, parents2)){
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
