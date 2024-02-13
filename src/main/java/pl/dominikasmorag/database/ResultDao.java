package pl.dominikasmorag.database;

import pl.dominikasmorag.pojo.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResultDao implements DAO<Result> {

    private Connection connection;

    public ResultDao(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Optional<Result> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Result> findAll() throws SQLException {
        List<Result> results = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT id, " +
                "location, " +
                "description, " +
                "sqm, " +
                "price, " +
                "link, " +
                "img_url, " +
                "posting_date, " +
                "web_scraping_timestamp " +
                "FROM " + DataBase.RESULTS_TABLE_NAME + ";");
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            results.add(new Result(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getFloat(4),
            rs.getBigDecimal(5),
            rs.getString(6),
            rs.getString(7),
            rs.getDate(8),
            rs.getTimestamp(9)));
        }
    return results;
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

    public void saveAll(List<Result> list) throws SQLException {
        for (Result r : list) {
            save(r);
        }
    }

    @Override
    public void delete(Result result) throws SQLException {

    }

    public List<String> findAllLinks() throws SQLException {
        List<String> linkList = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT link FROM " + DataBase.RESULTS_TABLE_NAME + ";");
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            linkList.add(rs.getString(1));
        }
        return linkList;
    }
}
