package pl.dominikasmorag.ui.command;

public class HelpCommand implements Command {
    private final static String TEXT_TO_DISPLAY = """
            help - displays all available commands
            generate-report [json, html, csv] - generates a report in desired format
            initiate-webscraping - starts webscraping
            exit - stops program""";
    @Override
    public void execute() {
        System.out.println(TEXT_TO_DISPLAY);
    }
}
