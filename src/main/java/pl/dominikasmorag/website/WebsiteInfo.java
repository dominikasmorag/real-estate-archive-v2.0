package pl.dominikasmorag.website;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebsiteInfo {
    private static final int FIRST_PAGE = 1;
    int numberOfPage;
    String URL1 = "https://domy.pl/mieszkania-sprzedaz-katowice-pl?page=";
    String URL2 = "&ps[advanced_search]=1&ps[sort_order]=rank&ps[sort_asc]=0&ps[location][type]=1&ps[location][text_queue][0]=Katowice&ps[location][text_tmp_queue][0]=Katowice&ps[transaction]=1&ps[type]=1&ps[price_from]=200000&ps[price_to]=500000&limit=75";


    public WebsiteInfo() throws IOException {
    }

    /** calculates the number of pages **/
    int pages() throws IOException {
        int pages = 0;
        Document doc = Jsoup.connect(URL1 + FIRST_PAGE + URL2).timeout(10 * 1000).get();
        int numberOfOffers = offersNumber(doc);
        do {
            pages++;
            numberOfOffers -= 75;
        } while(numberOfOffers>0);
        System.out.println("PAGES: " + pages);
        return pages;
    }

    int offersNumber(Document doc) {
        String offersFound = doc.getElementsByAttributeValue("class", "offersFound").tagName("b").text().replaceAll("[^0-9]", "");
        return Integer.parseInt(offersFound);
    }

    public List<String> urlWithPages() {
        List<String> list = new ArrayList<>();
        try {
            int numberOfPages = pages();
            for (int i = 1; i <= numberOfPages; i++) {
                numberOfPage = i;
                list.add(URL1 + i + URL2);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
