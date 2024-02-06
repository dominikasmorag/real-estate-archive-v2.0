package pl.dominikasmorag.DataBase;

import pl.dominikasmorag.pojo.Result;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                "(LOCATION, SQM, PRICE, LINK, IMG_URL, POSTING_DATE, DURATION) VALUES (?, ?, ?, ?, ?, ?, ?");
        statement.setString(1, result.getLocation());
        statement.setFloat(2, result.getSquareFootage());
        statement.setBigDecimal(3, result.getPrice());
        statement.setString(4, result.getLink());
        statement.setString(5, result.getImgUrl());
        statement.setDate(6, (Date) result.getPostingDate());
        statement.setLong(7, result.getDuration());
        statement.executeUpdate();
    }

    @Override
    public void delete(Result result) throws SQLException {

    }
}
