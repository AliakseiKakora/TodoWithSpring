package by.itacademy.todolist.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfo {
    private long id;
    private String path;
}