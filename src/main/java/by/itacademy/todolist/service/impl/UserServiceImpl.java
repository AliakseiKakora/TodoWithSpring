package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.dao.UserDao;
import by.itacademy.todolist.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao<User> userDao;

    public UserServiceImpl(UserDao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        return userDao.getUserByLoginAndPassword(login, password);
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User update(User user, String email, String name, String surname) {
        if (user.getEmail().equals(email)
                && user.getSurname().equals(surname)
                && user.getName().equals(name)) {
            return user;
        }
        user.setEmail(email);
        user.setSurname(surname);
        user.setName(name);
        return userDao.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

}
