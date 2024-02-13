package pl.dominikasmorag.ui.controller;

import org.h2.jdbcx.JdbcDataSource;
import pl.dominikasmorag.database.DataBase;
import pl.dominikasmorag.database.ResultDao;
import pl.dominikasmorag.ui.command.Command;
import pl.dominikasmorag.ui.command.CommandFactory;
import pl.dominikasmorag.website.ResultInfo;
import pl.dominikasmorag.website.WebsiteInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Controller {
    static ResultDao resultDao;
    static ResultInfo resultInfo;
    private static Connection connection;

    public static void initialize(JdbcDataSource dataSource) {
        try {
            connection = dataSource.getConnection();
            DataBase db = new DataBase(connection);
            db.createSchema();
            resultDao = new ResultDao(connection);
            WebsiteInfo websiteInfo = WebsiteInfo.getInstance();
            resultInfo = new ResultInfo(websiteInfo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void allowCommands() {
        String userInput = "";
        while (!("exit".equalsIgnoreCase(userInput))) {
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            try {
                Command command = CommandFactory.createCommand(userInput, resultDao);
                command.execute();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ResultDao getResultDao() {
        return resultDao;
    }

}
