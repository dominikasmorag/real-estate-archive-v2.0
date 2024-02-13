package pl.dominikasmorag.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> findById(int id) throws SQLException;

    List<T> findAll() throws SQLException;

    void save(T t) throws SQLException;

    void saveAll(List<T> list) throws SQLException;

    void delete(T t) throws SQLException;
}
