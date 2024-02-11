package pl.dominikasmorag.ui.export;


import pl.dominikasmorag.DataBase.DAO;
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
     List<Result> resultList;
     DAO<Result> dao;
     private final SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
     protected String fileName = formatter.format(new Date(System.currentTimeMillis()));
     protected String filePath = "C:\\Users\\domin\\Desktop\\real_estate_archive_reports\\";
     public ExportStrategy(DAO<Result> dao) {
          this.dao = dao;
     }

     protected File createFile() throws IOException {
          File f = new File(filePath + fileName);
          if(!f.exists()) {
               f.createNewFile();
          }
          return f;
     }

      protected void collectData() {
          try {
               resultList = dao.findAll();
          } catch (SQLException ex) {
               ex.printStackTrace();
          }
      };

     public abstract void export()  throws IOException;


     protected BufferedWriter createBufferedWriter(File file) {
          try {
               System.out.println("FILE NAME " + file.getPath());
               System.out.println("Tried to bw");
               return new BufferedWriter(new FileWriter(file));
          } catch (IOException ex) {
               ex.printStackTrace();
          }
          return null;
     }

     abstract void saveDataToFile(BufferedWriter bufferedWriter);

}
