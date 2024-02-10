package pl.dominikasmorag.user_utilities.command;

import pl.dominikasmorag.user_utilities.export.ExportStrategy;

import java.io.IOException;

public class ExportCommand implements Command {
    private final ExportStrategy exportStrategy;

    public ExportCommand(ExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }
    @Override
    public void execute() {
        try {
            exportStrategy.export();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ExportStrategy getExportStrategy() {
        return exportStrategy;
    }

    @Override
    public String toString() {
        return "ExportCommand{" +
                "exportStrategy=" + exportStrategy +
                '}';
    }
}
