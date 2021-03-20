package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.persistence.dao.RoleDao;
import by.itacademy.todolist.service.RoleService;

public class RoleServiceImpl implements RoleService {

    private final RoleDao<Role> roleDao;

    public RoleServiceImpl(RoleDao<Role> roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getByNameWithUsers(String role) {
        return roleDao.getByNameWithUsers(role);
    }

    @Override
    public void deleteAllUserRoles(long userId) {
        roleDao.deleteAllUserRoles(userId);
    }

}
