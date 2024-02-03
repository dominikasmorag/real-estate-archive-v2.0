package pl.dominikasmorag;

import org.jsoup.select.Elements;
import pl.dominikasmorag.website.ResultInfo;
import pl.dominikasmorag.website.WebsiteInfo;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            WebsiteInfo websiteInfo = new WebsiteInfo();
            List<String> urls = websiteInfo.urlWithPages();
            ResultInfo resultInfo = new ResultInfo(websiteInfo);
            Elements elements = resultInfo.findElements();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}