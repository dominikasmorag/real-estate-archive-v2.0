package pl.dominikasmorag.user_utilities.export;

import pl.dominikasmorag.DataBase.DAO;
import pl.dominikasmorag.pojo.Result;

import java.io.*;

public class ExportHTML extends ExportStrategy {
    private final static String EXTENSION = ".html";

    public ExportHTML(DAO<Result> resultDao) {
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
            bufferedWriter.write("test html");
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
