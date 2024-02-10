package pl.dominikasmorag.user_utilities.export;

import pl.dominikasmorag.DataBase.DAO;
import pl.dominikasmorag.pojo.Result;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class ExportCSV extends ExportStrategy {

    private final static String EXTENSION = ".csv";

    public ExportCSV(DAO<Result> resultDao) {
        super(resultDao);
        fileName += EXTENSION;
    }

    @Override
    public void export() {
        try {
            collectData();
            File file =  createFile();
            BufferedWriter bf = createBufferedWriter(file);
            saveDataToFile(bf);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void saveDataToFile(BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write("test csv");
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
