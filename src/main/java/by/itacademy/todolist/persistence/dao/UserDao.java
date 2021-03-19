package by.itacademy.todolist.persistence.dao;

public interface UserDao<User> extends CrudDao<User> {

    User getUserByLoginAndPassword(String login, String password);

}