package pl.dominikasmorag.ui.export;

import pl.dominikasmorag.DataBase.DAO;
import pl.dominikasmorag.pojo.Result;

import java.io.*;



public class ExportHTML extends ExportStrategy {
    private final static String EXTENSION = ".html";
    private final static String OPEN_TABLE = "<html> <body> <table>";
    private final static String CLOSE_TABLE = "</table> </body> </html>";
    private final static String[] COLUMN_NAMES = {"Id", "Location", "Description", "Square meters", "Price [PLN]", "Link", "Image url", "Posting date", "DateTime of scraping"};
    private final static String CSS = "<style>" +
            "table { border-collapse: collapse;" +
            "       width: 100%; }" +
            "th, td { border: 1px solid #dddddd;" +
            "        text-align: left;" +
            "        padding: 8px; }" +
            "th { background-color: #f2f2f2; }" +
            "</style>";

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
            bufferedWriter.write(String.valueOf(createTable()));
            bufferedWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private StringBuilder createTable() {
        StringBuilder sb = new StringBuilder(OPEN_TABLE);
        sb.append(CSS);
        sb.append("<tr>");
        for(String s : COLUMN_NAMES) {
            sb.append("<th>").append(s).append("</th>");
        }
        sb.append("</tr>");

        for(int i=0; i<resultList.size(); i++) {
            sb.append("<tr>");
            sb.append("<td>").append(resultList.get(i).getId()).append("</td>");
            sb.append("<td>").append(resultList.get(i).getLocation()).append("</td>");
            sb.append("<td>").append(resultList.get(i).getDescription()).append("</td>");
            sb.append("<td>").append(resultList.get(i).getSquareFootage()).append("</td>");
            sb.append("<td>").append(resultList.get(i).getPrice()).append("</td>");
            sb.append("<td>").append(resultList.get(i).getLink()).append("</td>");
            sb.append("<td>").append(resultList.get(i).getImgUrl()).append("</td>");
            sb.append("<td>").append(resultList.get(i).getPostingDate()).append("</td>");
            sb.append("<td>").append(resultList.get(i).getTimestamp()).append("</td>");
            sb.append("</tr>");
        }
        sb.append(CLOSE_TABLE);

        return sb;
    }

}
