package pl.dominikasmorag.ui.export;


import pl.dominikasmorag.database.DAO;
import pl.dominikasmorag.pojo.Result;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public abstract class ExportStrategy {
     protected List<Result> resultList;

     private final DAO<Result> dao;
     private final SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
     protected String fileName = formatter.format(new Date(System.currentTimeMillis()));
     protected String filePath = "C:\\Users\\domin\\Desktop\\real_estate_archive_reports\\";

     protected final static String[] COLUMN_NAMES = {"Id",
             "Location",
             "Description",
             "Square meters",
             "Price [PLN]",
             "Link",
             "Image url",
             "Posting date",
             "DateTime of scraping"};
     public ExportStrategy(DAO<Result> dao) {
          this.dao = dao;
     }

     protected File createFile() throws IOException {
          return new File(filePath + fileName);
     }

      protected void collectData() {
          try {
               resultList = dao.findAll();
          } catch (SQLException ex) {
               ex.printStackTrace();
          }
      }

     public abstract void export()  throws IOException;

     protected BufferedWriter createBufferedWriter(File file) {
          try {
               System.out.println("File path: " + file.getPath());
               return new BufferedWriter(new FileWriter(file));
          } catch (IOException ex) {
               ex.printStackTrace();
          }
          return null;
     }

     abstract void saveDataToFile(BufferedWriter bufferedWriter);

}
