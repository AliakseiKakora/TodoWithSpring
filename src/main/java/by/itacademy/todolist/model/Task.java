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
public class Task {
    private long id;
    private String name;
    private String description;
    private LocalDateTime dateAdded;
    private LocalDateTime dateCompletion;
    private FileInfo fileInfo;
    private boolean isCompleted;
    private boolean isDeleted;
}