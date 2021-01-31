package by.itacademy.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private Profile profile;
    private List<Role> roles;
    private List<Task> tasks;
}