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
        assertEquals(todayDate, dateConverter.convertDate("DziSiaj  "));
    }

    @Test
    void wrongDateFormatReturnsNull() {
        assertNull(dateConverter.convertDate("12-30-2023"));
    }

    @Test
    void emptyStringReturnsNull() {
        assertNull(dateConverter.convertDate(""));
    }
}