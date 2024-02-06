package pl.dominikasmorag;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateConverterTest {
    DateConverter dateConverter = new DateConverter();
    LocalDate currentDate = LocalDate.now();

    @Test
    void yesterdayReturnsYesterdayDate() {
        Date yesterdayDate = Date.from(currentDate.minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        assertEquals(yesterdayDate, dateConverter.convertDate(" wCzOraJ "));
    }

    @Test
    void todayReturnsTodayDate() {
        Date todayDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        assertEquals(todayDate, dateConverter.convertDate(" DZisIaj "));
    }

    @Test
    void wrongDateFormatReturnsNull() {
        Date todayDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(todayDate);
        assertNull(dateConverter.convertDate("12-30-2024"));
        assertNull(dateConverter.convertDate("2024-12-30"));
        assertNull(dateConverter.convertDate("30-2024-12"));
    }

    @Test
    void nullReturnsNull() {
        assertNull(null);
    }

}