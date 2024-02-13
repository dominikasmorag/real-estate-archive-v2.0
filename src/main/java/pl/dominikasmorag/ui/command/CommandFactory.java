package pl.dominikasmorag.ui.command;

import pl.dominikasmorag.database.DAO;
import pl.dominikasmorag.database.ResultDao;
import pl.dominikasmorag.pojo.Result;
import pl.dominikasmorag.ui.controller.Controller;
import pl.dominikasmorag.ui.export.ExportCSV;
import pl.dominikasmorag.ui.export.ExportHTML;
import pl.dominikasmorag.ui.export.ExportJSON;
import pl.dominikasmorag.ui.export.ExportStrategy;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private final static Map<String, ExportStrategy> strategyMap = new HashMap<>();

    public static Command createCommand(String userInput, ResultDao resultDao) {
        fillStrategyMap(resultDao);
        return getCommand(userInput);
    }

    private static Command getCommand(String userInput) throws NullPointerException {
        if(userInput.startsWith("generate-report")) {
            String[] splitter = userInput.split("\\s");
            userInput = splitter[1];
            return new ExportCommand(getExportStrategy(userInput));
        }
        else if(userInput.equalsIgnoreCase("initiate-webscraping")) {
            return new ScrapingCommand(Controller.getResultDao());
        }
        return null;
    }

    private static ExportStrategy getExportStrategy(String exportArg) {
        exportArg = exportArg.toLowerCase().trim();
        try {
            return strategyMap.get(exportArg);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static void fillStrategyMap(DAO<Result> resultDao) {
        strategyMap.put("html", new ExportHTML(resultDao));
        strategyMap.put("csv", new ExportCSV(resultDao));
        strategyMap.put("json", new ExportJSON(resultDao));
    }

}
