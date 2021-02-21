package by.itacademy.todolist.persistance.dao;

public interface UserDao<User> extends CrudDao<User> {
    User getUserByLoginAndPassword(String login, String password);
}
