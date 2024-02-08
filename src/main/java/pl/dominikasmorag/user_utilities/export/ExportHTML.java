package pl.dominikasmorag.user_utilities.export;

import pl.dominikasmorag.DataBase.DAO;
import pl.dominikasmorag.pojo.Result;

import java.sql.SQLException;
import java.util.List;

public class ExportHTML implements ExportStrategy {
    public List<Result> resultList;
    private DAO<Result> dao;

    public ExportHTML(DAO<Result> dao) {
        this.dao = dao;
    }
    @Override
    public void collectData() {
        try {
            this.resultList = dao.findAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void export() {

    }
}
