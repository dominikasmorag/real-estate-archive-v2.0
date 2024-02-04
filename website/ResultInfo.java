package pl.dominikasmorag.website;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.dominikasmorag.DateConverter;
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
                Date date = findPostingDate(element);
                Result result = new Result();
                result.setLocation(location);
                result.setDescription(description);
                result.setSquareFootage(squareFootage);
                result.setPrice(price);
                result.setLink(link);
                result.setImgUrl(imgUrl);
                result.setPostingDate(date);
                results.add(result);
            }
        }
        for(Result r : results) {
            System.out.println(r);
        }
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
        Elements e = element.getElementsByAttributeValue("class", "PhotoThumbnail");
        return e.select("img").attr("data-original");

    }

    private Date findPostingDate(Element element) {
        String dateStr = element.getElementsByAttributeValue("class", "addedAt").text();
        dateStr.trim();
        DateConverter dateConverter = new DateConverter();
        Date date = dateConverter.convertDate(dateStr);
        return date;
    }
}
