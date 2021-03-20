package by.itacademy.todolist.service;

import by.itacademy.todolist.model.Role;

public interface RoleService {

    Role getByNameWithUsers(String role);

    void deleteAllUserRoles(long userId);

}
