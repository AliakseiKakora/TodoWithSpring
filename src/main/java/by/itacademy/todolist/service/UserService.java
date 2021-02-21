package by.itacademy.todolist.service;

import by.itacademy.todolist.model.User;

public interface UserService {

    User getUserByLoginAndPassword(String login, String password);

    User create(User user);
}
