package pl.dominikasmorag.website;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.dominikasmorag.pojo.Result;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultInfo {
    static List<String> urls;
    public Elements elements;
    private Document document;

    public ResultInfo(WebsiteInfo websiteInfo) throws IOException {
        urls = websiteInfo.urlWithPages();
        List<Result> results = new ArrayList<>();
        for(String s : urls) {
            document = Jsoup.connect(s).timeout(10 * 1000).get();
            elements = findElements();
            for(Element element : elements) {
                String location = findLocation(element);
                String description = findDescription(element);
                float squareFootage = findSquareFootage(element);
                BigDecimal price = findPrice(element);
                String link = findLink(element);
                String imgUrl = findImgUrl(element);
                Result result = new Result();
                result.setLocation(location);
                result.setDescription(description);
                result.setSquareFootage(squareFootage);
                result.setPrice(price);
                result.setLink(link);
                result.setImgUrl(imgUrl);
                results.add(result);
            }
        }
//        for(Result r : results) {
//            System.out.println(r);
//        }
    }

    public Elements findElements() {
        return document.getElementsByAttributeValueContaining("class", "propertyBox");
    }

    private String findLocation(Element element) {
        return element.getElementsByAttributeValue("class", "property_link").text();
    }

    private String findDescription(Element element) {
        return element.getElementsByAttributeValue("class", "description").text();
    }

    private float findSquareFootage(Element element) {
        return Float.parseFloat(element.getElementsByAttributeValue("class", "area").text().replaceAll("[^0-9,]", "").replace(",", "."));
    }

    private BigDecimal findPrice(Element element) {
        String price = element.getElementsByAttributeValue("class", "price").text();
        if("inf. u dewelopera".equalsIgnoreCase(price)) {
            return null;
        }
        else {
            return new BigDecimal(price.replaceAll("[^0-9,]", "").replace(",", "."));
        }
    }

    private String findLink(Element element) {
        return element.getElementsByAttributeValue("class", "property_link").attr("href");
    }

    private String findImgUrl(Element element) {
        System.out.println("findImgUrl()");
        System.out.println(element.tagName("img").getElementsByAttributeValue("class", "").attr("src"));
        return element.getElementsByAttributeValue("class", "PhotoThumbnail").attr("src");
    }

    private Date findPostingDate() {
        return new Date();
    }
}
