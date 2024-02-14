package pl.dominikasmorag.ui.command;

public class DisplayCommand implements Command {
    @Override
    public void execute() {
        System.out.println("help - displays all available commands\n" +
                "generate-report [json, html, csv] - generates a report in desired format\n" +
                "initiate-webscraping - starts webscraping\n" +
                "exit - stops program");
    }
}
