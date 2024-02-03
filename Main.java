package pl.dominikasmorag;

import pl.dominikasmorag.website.ResultInfo;
import pl.dominikasmorag.website.WebsiteInfo;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            WebsiteInfo websiteInfo = new WebsiteInfo();
            ResultInfo resultInfo = new ResultInfo(websiteInfo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}