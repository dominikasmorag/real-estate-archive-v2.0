package pl.dominikasmorag.DataBase;

import pl.dominikasmorag.pojo.Result;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ResultDao implements DAO<Result> {

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
                "(LOCATION, DESCRIPTION, SQM, PRICE, LINK, IMG_URL, POSTING_DATE, WEB_SCRAPING_TIMESTAMP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, result.getLocation());
        statement.setString(2, result.getDescription());
        statement.setFloat(3, result.getSquareFootage());
        statement.setBigDecimal(4, result.getPrice());
        statement.setString(5, result.getLink());
        statement.setString(6, result.getImgUrl());
        java.util.Date date = result.getPostingDate();
        statement.setDate(7, new Date(date.getYear(), date.getMonth(), date.getDate()));
        statement.setTimestamp(8, result.getTimestamp());
        statement.executeUpdate();
    }

    @Override
    public void delete(Result result) throws SQLException {

    }
}
