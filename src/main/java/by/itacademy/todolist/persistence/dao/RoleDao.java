package by.itacademy.todolist.persistence.dao;

public interface RoleDao<Role> extends CrudDao<Role> {

    Role getByIdWithUsers(long id);

    Role getByNameWithUsers(String role);

    void deleteAllUserRoles(long userId);
}