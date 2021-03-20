package by.itacademy.todolist.persistence.dao;

import by.itacademy.todolist.model.User;

import java.util.List;

public interface RoleDao<Role> extends CrudDao<Role> {

    Role getByIdWithUsers(long id);

    Role getByNameWithUsers(String role);

    List<Role> getRolesByUserId(long id);

    void addUserRoles(User user);

    void deleteAllUserRoles(long userId);
}