package by.itacademy.todolist.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This class provide functions that return date and time in end day format.
 * Example - tomorrow date would be returned as "XX.XX(tomorrow day).23.59"
 */
public class DateParserImpl implements DateParser {

    private static final String DATE_FORMAT = "%sT23:59";
    private static final String TODAY = String.format(DATE_FORMAT, LocalDate.now().toString());

    @Override
    public LocalDateTime getLocalDateTime(String date, String time) {
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        return LocalDateTime.of(localDate, localTime);
    }

    @Override
    public LocalDateTime getTodayLocalDateTime() {
        return LocalDateTime.parse(TODAY);
    }

    @Override
    public LocalDateTime getTomorrowLocalDateTime() {
        return LocalDateTime.parse(TODAY).plusDays(1);
    }
}