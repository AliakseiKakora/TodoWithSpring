package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistence.dao.UserDao;
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
        if (!isValidRegistrationData(user)) {
            throw new RuntimeException("User credentials are not valid ");
        }
        return userDao.save(user);
    }

    private boolean isValidRegistrationData(User user) {
        if (user == null || user.getProfile() == null) {
            return false;
        }
        return user.getEmail() != null && !user.getEmail().equals("")
                && user.getProfile().getLogin() != null
                && user.getProfile().getPassword() != null
                && !user.getProfile().getLogin().equals("")
                && !user.getProfile().getPassword().equals("");
    }

    @Override
    public User update(User user) {
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new RuntimeException("Email not cannot be empty");
        }

        return userDao.update(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }
}
