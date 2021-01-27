package by.itacademy.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String description;
    private LocalDateTime dateAdded;
    private LocalDateTime dateCompletion;
    private File file;
    private boolean isCompleted;
    private boolean isDeleted;
}