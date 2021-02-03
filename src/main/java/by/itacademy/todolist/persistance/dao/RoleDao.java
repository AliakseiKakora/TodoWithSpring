package by.itacademy.todolist.persistance.dao;

import by.itacademy.todolist.model.User;

import java.util.List;

public interface RoleDao<Role> extends CrudDao<Role> {

    List<Role> getRolesByUserId(long id);

    void addUserRoles(User user);

    void deleteAllUserRoles(long userId);
}