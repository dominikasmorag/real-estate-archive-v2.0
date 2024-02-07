package pl.dominikasmorag;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.h2.jdbcx.JdbcDataSource;
import pl.dominikasmorag.DataBase.DAO;
import pl.dominikasmorag.DataBase.DataBase;
import pl.dominikasmorag.DataBase.ResultDao;
import pl.dominikasmorag.pojo.Result;
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
            WebsiteInfo websiteInfo = WebsiteInfo.getInstance();
            ResultInfo resultInfo = new ResultInfo(websiteInfo);
            List<Result> results = resultInfo.scrapAllResults();
            DAO<Result> resultDAO = new ResultDao(connection);

            for(Result r : results) {
                try {
                    resultDAO.save(r);
                } catch (JdbcSQLIntegrityConstraintViolationException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

}