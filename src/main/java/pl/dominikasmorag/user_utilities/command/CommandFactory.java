package pl.dominikasmorag.user_utilities.command;

import pl.dominikasmorag.DataBase.DAO;
import pl.dominikasmorag.DataBase.ResultDao;
import pl.dominikasmorag.user_utilities.export.ExportCSV;
import pl.dominikasmorag.user_utilities.export.ExportHTML;
import pl.dominikasmorag.user_utilities.export.ExportJSON;
import pl.dominikasmorag.user_utilities.export.ExportStrategy;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    static Map<String, ExportStrategy> strategyMap = new HashMap<>();

    public static Command createCommand(String userInput, ResultDao resultDAO) {
        fillStrategyMap(resultDAO);
        return getCommand(userInput);
    }

    private static Command getCommand(String userInput) {
        if(userInput.contains("generate-report")) {
            String[] splitter = userInput.split("\\s");
            userInput = splitter[1];
            return new ExportCommand(getExportStrategy(userInput));
        }
        else if(userInput.equalsIgnoreCase("start-webscraping")) {
            System.out.println("not available yet");
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

    private static void fillStrategyMap(DAO resultDao) {
        strategyMap.put("html", new ExportHTML(resultDao));
        strategyMap.put("csv", new ExportCSV(resultDao));
        strategyMap.put("json", new ExportJSON(resultDao));
    }

}
