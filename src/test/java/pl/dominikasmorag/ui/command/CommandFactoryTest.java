package pl.dominikasmorag.ui.command;

import org.junit.jupiter.api.Test;
import pl.dominikasmorag.database.ResultDao;
import pl.dominikasmorag.ui.export.ExportCSV;
import pl.dominikasmorag.ui.export.ExportHTML;
import pl.dominikasmorag.ui.export.ExportJSON;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    Connection conn = null;
    ResultDao resultDao = new ResultDao(conn);


    @Test
    void generateReportHtmlGeneratesHtml() {
        ExportCommand exportCommandHTML = (ExportCommand)  CommandFactory.createCommand("generate-report html", resultDao);
        assertInstanceOf(ExportHTML.class, exportCommandHTML.getExportStrategy());
    }

    @Test
    void generateReportCsvGeneratesCsv() {
        ExportCommand exportCommandCSV = (ExportCommand)  CommandFactory.createCommand("generate-report csv", resultDao);
        assertInstanceOf(ExportCSV.class, exportCommandCSV.getExportStrategy());
    }

    @Test
    void generateReportJsonGeneratesJson() {
        ExportCommand exportCommandJSON = (ExportCommand)  CommandFactory.createCommand("generate-report json", resultDao);
        assertInstanceOf(ExportJSON.class, exportCommandJSON.getExportStrategy());
    }

    @Test
    void anythingElseGeneratesNull() {
        ExportCommand exportCommand = (ExportCommand) CommandFactory.createCommand("generate-report jso", resultDao);
        assertNull(exportCommand.getExportStrategy());
    }


}