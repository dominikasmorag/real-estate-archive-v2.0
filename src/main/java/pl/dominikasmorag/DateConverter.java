package pl.dominikasmorag;


import java.text.ParseException;
import java.util.Date;

public class DateConverter extends Date {
    private static final long FULL_DAY_IN_MILLIS = 864 * 100000;

    public Date convertDate(String dateStr) {
        dateStr = dateStr.toLowerCase().trim();
        if(dateStr.contains(DayOfWeek.YESTERDAY.name) || dateStr.contains(DayOfWeek.TODAY.name)) {
            try {
                dateStr = dateStr.replaceAll("data: ", "");
                return dateByDayOfWeek(dateStr);
            } catch(ParseException ex) {
                System.err.println("bug in " + this.getClass().getName() + " " + dateStr);
                ex.printStackTrace();
                return null;
            }
        }
        else {
            try {
                dateStr = dateStr.replaceAll("[^0-9-]", "");
                return dateByFullDate(dateStr);
            } catch (ParseException | ArrayIndexOutOfBoundsException ex) {
                System.err.println("bug in " + this.getClass().getName() + " else statement " + dateStr);
                ex.printStackTrace();
                return null;
            }
        }
    }

    private Date dateByDayOfWeek(String dateStr) throws ParseException {

        dateStr = dateStr.trim();
        if(dateStr.equalsIgnoreCase(DayOfWeek.YESTERDAY.name)) {
            return turnIntoFulLDate(new Date(System.currentTimeMillis() - FULL_DAY_IN_MILLIS));
        }
        else if(dateStr.equalsIgnoreCase(DayOfWeek.TODAY.name)) {
            return turnIntoFulLDate(new Date(System.currentTimeMillis()));
        }
        return null;
    }

    private Date dateByFullDate(String dateStr) throws ParseException {
        String[] splitted = dateStr.split("-");
        int year = Integer.parseInt(splitted[2]);
        int month = Integer.parseInt(splitted[1]);
        int day = Integer.parseInt(splitted[0]);
        if(day > 31 || month > 12 || year < 2020 ) {
            return null;
        }

        return new Date(year - 1900, month - 1, day);
    }

    private Date turnIntoFulLDate(Date date) {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }

    enum DayOfWeek {
        YESTERDAY("wczoraj"),
        TODAY("dzisiaj");
        private final String name;

        DayOfWeek(String name) {
            this.name = name;
        }
    }

}
