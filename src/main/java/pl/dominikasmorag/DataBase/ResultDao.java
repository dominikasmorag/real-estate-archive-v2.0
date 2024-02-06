package pl.dominikasmorag.DataBase;

import pl.dominikasmorag.pojo.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ResultDao implements DAO<Result> {
    /*
    "LOCATION VARCHAR(200)," +
            "DESCRIPTION VARCHAR(300)," +
            "SQM FLOAT," +
            "PRICE DECIMAL," +
            "LINK VARCHAR(400) UNIQUE," +
            "IMG_URL VARCHAR(300)," +
            "POSTING_DATE DATE," +
            "DURATION_IN_MILLIS LONG);" ;
     */
    private Connection connection;

    public ResultDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Optional<Result> get(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Result> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Result result) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + DataBase.RESULTS_TABLE_NAME +
                "(LOCATION, SQM, PRICE, LINK, IMG_URL, POSTING_DATE, DURATION) VALUES (?, ?, ?, ?, ?, ?, ?");
    }

    @Override
    public void delete(Result result) throws SQLException {

    }
}
