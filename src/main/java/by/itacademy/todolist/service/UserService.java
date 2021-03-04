package by.itacademy.todolist.service;

import by.itacademy.todolist.model.User;

import java.util.List;

public interface UserService {

    User getUserByLoginAndPassword(String login, String password);

    User create(User user);

    User update(User user, String email, String name, String surname);

    List<User> getAllUsers();
}
