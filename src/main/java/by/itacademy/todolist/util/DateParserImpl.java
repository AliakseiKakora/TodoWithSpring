package by.itacademy.todolist.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * This class provide functions that return date and time in end day format.
 * Example - tomorrow date would be returned as "XX.XX(tomorrow day).23.59"
 */
public class DateParserImpl implements DateParser {

    private static final String DATE_FORMAT = "%sT23:59";
    private static final String TODAY = String.format(DATE_FORMAT, LocalDate.now().toString());

    @Override
    public LocalDateTime getLocalDateTime(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return LocalDateTime.parse(String.format(DATE_FORMAT, localDate));
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