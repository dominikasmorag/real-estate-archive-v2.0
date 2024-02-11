package pl.dominikasmorag.ui.export;

import pl.dominikasmorag.DataBase.DAO;
import pl.dominikasmorag.pojo.Result;

import java.io.BufferedWriter;
import java.io.IOException;

public class ExportJSON extends ExportStrategy {

    public ExportJSON(DAO<Result> resultDao) {
        super(resultDao);
    }

    @Override
    public void export() {
    }
    protected void saveDataToFile(BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write("test json");
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
