package pl.dominikasmorag.ui.command;

import pl.dominikasmorag.ui.export.ExportStrategy;

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

}
