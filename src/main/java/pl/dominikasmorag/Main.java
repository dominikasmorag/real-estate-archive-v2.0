package pl.dominikasmorag;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.h2.jdbcx.JdbcDataSource;
import pl.dominikasmorag.DataBase.DataBase;
import pl.dominikasmorag.DataBase.ResultDao;
import pl.dominikasmorag.pojo.Result;
import pl.dominikasmorag.user_utilities.export.ExportHTML;
import pl.dominikasmorag.website.ResultInfo;
import pl.dominikasmorag.website.WebsiteInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:C:/Users/domin/git/real-estate-archive-v2.0");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");

        try {
            Connection connection = dataSource.getConnection();
            DataBase db = new DataBase(connection);
            db.createSchema();
            ResultDao resultDAO = new ResultDao(connection);
            WebsiteInfo websiteInfo = WebsiteInfo.getInstance();
            ResultInfo resultInfo = new ResultInfo(websiteInfo);
            List<Result> results = resultInfo.scrapAllResults(resultDAO.findAllLinks());

            for(Result r : results) {
                try {
                    resultDAO.save(r);
                } catch (JdbcSQLIntegrityConstraintViolationException ex) {
                    System.err.println(ex.getMessage());
                }
            }
                System.out.println("getting data from db: ");
                ExportHTML exportHtml = new ExportHTML(resultDAO);
                exportHtml.collectData();
                for(Result r :exportHtml.resultList) {
                    System.out.println(r);
                }

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

}