package by.itacademy.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Message {
    private long id;
    private String message;
    private LocalDateTime dateAdded;
    private User user;
}
