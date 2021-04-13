package by.itacademy.todolist.util;

import java.time.LocalDateTime;

public interface DateParser {

    LocalDateTime getLocalDateTime(String date, String time);

    LocalDateTime getTodayLocalDateTime();

    LocalDateTime getTomorrowLocalDateTime();

}