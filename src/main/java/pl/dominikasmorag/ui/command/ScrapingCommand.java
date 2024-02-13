package pl.dominikasmorag.ui.command;

import pl.dominikasmorag.database.ResultDao;
import pl.dominikasmorag.pojo.Result;
import pl.dominikasmorag.website.ResultInfo;
import pl.dominikasmorag.website.WebsiteInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ScrapingCommand implements Command {
    private final ResultDao resultDao;

    public ScrapingCommand(ResultDao resultDao) {
        this.resultDao = resultDao;
    }
    @Override
    public void execute() {
        System.out.println("Scraping started, please wait...");
        ResultInfo resultInfo = new ResultInfo(WebsiteInfo.getInstance());
        try {
            List<Result> results = resultInfo.scrapAllResults(resultDao.findAllLinks());
            resultDao.saveAll(results);
        } catch (IOException |SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Scraping finished");
    }
}
