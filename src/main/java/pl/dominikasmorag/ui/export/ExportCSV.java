package pl.dominikasmorag.ui.export;

import com.opencsv.CSVWriter;
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
            fillAllFields(bufferedWriter);
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void fillAllFields(BufferedWriter bufferedWriter) throws IOException {
        CSVWriter writer = new CSVWriter(bufferedWriter);
        writer.writeNext(COLUMN_NAMES);
        for(Result r : resultList) {
            writer.writeNext(new String[]{ String.valueOf(r.getId()),
            r.getLocation(),
            r.getDescription(),
            String.valueOf(r.getSquareFootage()),
            String.valueOf(r.getPrice()),
            r.getLink(),
            r.getImgUrl(),
            String.valueOf(r.getPostingDate()),
            String.valueOf(r.getTimestamp())});
        }
        writer.close();
    }

}
