package pl.dominikasmorag;

import org.h2.jdbcx.JdbcDataSource;
import pl.dominikasmorag.DataBase.DataBase;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        /** args[0] - url, args[1] username, args[2] - password */

        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:C:/Users/domin/git/real-estate-archive-v2.0");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");

        try {
            Connection connection = dataSource.getConnection();
            DataBase db = new DataBase(connection);
            db.createSchema();
            System.out.println(connection.isClosed());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


//        try {
//            WebsiteInfo websiteInfo = new WebsiteInfo();
//            ResultInfo resultInfo = new ResultInfo(websiteInfo);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

    }

}