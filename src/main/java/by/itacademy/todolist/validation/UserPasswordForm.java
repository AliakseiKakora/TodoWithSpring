package by.itacademy.todolist.validation;

import lombok.Data;

@Data
public class UserPasswordForm {
    @ValidPassword
    private String password;
}