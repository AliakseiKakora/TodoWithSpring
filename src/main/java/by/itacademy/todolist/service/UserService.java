package by.itacademy.todolist.service;

import by.itacademy.todolist.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User update(User user);

    List<User> getAllUsers();

    User getById(long id);

    User getByLogin(String login);

    void deleteById(long id);
}
