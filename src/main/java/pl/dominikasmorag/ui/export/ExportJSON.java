package pl.dominikasmorag.ui.export;

import pl.dominikasmorag.database.DAO;
import pl.dominikasmorag.pojo.Result;

import javax.json.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class ExportJSON extends ExportStrategy {
    private final static String EXTENSION = ".json";

    public ExportJSON(DAO<Result> resultDao) {
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
            bufferedWriter.write(buildJSON());
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String buildJSON() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Result r : resultList) {
            JsonObjectBuilder builder = Json.createObjectBuilder();
            if(r.getPrice() == null ) {
                r.setPrice(new BigDecimal(0));
            }
            builder.add(COLUMN_NAMES[0], r.getId())
                    .add(COLUMN_NAMES[1], r.getLocation())
                    .add(COLUMN_NAMES[2], r.getDescription())
                    .add(COLUMN_NAMES[3], r.getSquareFootage())
                    .add(COLUMN_NAMES[4], r.getPrice())
                    .add(COLUMN_NAMES[5], r.getLink())
                    .add(COLUMN_NAMES[6], r.getImgUrl())
                    .add(COLUMN_NAMES[7], String.valueOf(r.getPostingDate()))
                    .add(COLUMN_NAMES[8], String.valueOf(r.getTimestamp()));
            arrayBuilder.add(builder);
        }
        JsonArray jsonValues = arrayBuilder.build();
        return jsonValues.toString();
    }

}
