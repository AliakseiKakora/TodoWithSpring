package by.itacademy.todolist.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN(1, "ADMIN"),
    USER(2, "USER");

    private final long id;
    private final String role;

}