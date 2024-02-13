package pl.dominikasmorag;

import org.h2.jdbcx.JdbcDataSource;
import pl.dominikasmorag.ui.controller.Controller;

public class Main {
    static JdbcDataSource dataSource = new JdbcDataSource();

    public static void main(String[] args) {
        dataSource.setURL(args[0]);
        dataSource.setUser(args[1]);
        dataSource.setPassword(args[2]);

        Controller.initialize(dataSource);
        Controller.allowCommands();
    }

}