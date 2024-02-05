package pl.dominikasmorag;

import java.text.ParseException;
import java.util.Calendar;
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

        long now = System.currentTimeMillis();
        dateStr = dateStr.trim();
        if(dateStr.equalsIgnoreCase(DayOfWeek.YESTERDAY.name)) {
            long l = now - FULL_DAY_IN_MILLIS;
            return turnIntoFulLDate(new Date(l));
        }
        else if(dateStr.equalsIgnoreCase(DayOfWeek.TODAY.name)) {
            long l = now;
            return turnIntoFulLDate(new Date(l));
        }
        return null;
    }

    private Date dateByFullDate(String dateStr) throws ParseException {
        String[] splitted = dateStr.split("-");
        int year = Integer.parseInt(splitted[2]);
        int month = Integer.parseInt(splitted[1]);
        int day = Integer.parseInt(splitted[0]);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar.getTime();
    }

    private Date turnIntoFulLDate(Date date) {
        Date d = new Date(date.getYear(), date.getMonth(), date.getDate());
        return d;
    }

    enum DayOfWeek {
        YESTERDAY("wczoraj"),
        TODAY("dzisiaj");
        private String name;

        DayOfWeek(String name) {
            this.name = name;
        }
    }

}
