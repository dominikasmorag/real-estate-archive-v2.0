package pl.dominikasmorag.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final String CREATE_RESULT_TABLE =  "CREATE TABLE RESULTS (" +
            "ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            "LOCATION VARCHAR(200)," +
            "DESCRIPTION VARCHAR(300)," +
            "SQM FLOAT," +
            "PRICE DECIMAL," +
            "LINK VARCHAR(400) UNIQUE," +
            "IMG_URL VARCHAR(300)," +
            "POSTING_DATE DATE," +
            "DURATION_IN_MILLIS LONG);" ;
    private Statement statement;
    private final Connection connection;

    public DataBase(Connection connection) {
        this.connection = connection;
    }

    public void createSchema() throws SQLException {
        createTable();
    }

    private void createTable() throws SQLException  {
        this.statement = connection.createStatement();
        statement.executeUpdate(CREATE_RESULT_TABLE);
    }
}
