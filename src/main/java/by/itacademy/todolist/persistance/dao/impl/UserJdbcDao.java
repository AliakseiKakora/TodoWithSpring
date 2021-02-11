package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.dao.*;
import by.itacademy.todolist.persistance.mapper.impl.UserResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.UserSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.UserStatementInitializer;

import java.util.List;

public class UserJdbcDao extends AbstractJdbcDao<User> implements UserDao<User> {

    private final ProfileDao<Profile> profileDao;
    private final RoleDao<Role> roleDao;
    private final TaskDao<Task> taskDao;

    public UserJdbcDao(Connector connector, ProfileDao<Profile> profileDao, RoleDao<Role> roleDao, TaskDao<Task> taskDao) {
        super(connector, new UserResultSetMapper(), new UserSqlQueryHolder(), new UserStatementInitializer());
        this.profileDao = profileDao;
        this.roleDao = roleDao;
        this.taskDao = taskDao;
    }

    @Override
    public User create(User user) {
        Profile createdProfile = profileDao.create(user.getProfile());

        user.setProfile(createdProfile);
        User createdUser = super.create(user);
        createdUser.setRoles(user.getRoles());
        roleDao.addUserRoles(createdUser);
        return createdUser;
    }

    @Override
    public User getById(long id) {
        List<Role> usersRoles = roleDao.getRolesByUserId(id);
        User user = super.getById(id);
        user.setRoles(usersRoles);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = super.getAll();
        users.forEach(u -> u.setRoles(roleDao.getRolesByUserId(u.getId())));
        return users;
    }

    @Override
    public void delete(long id) {
        User user = getById(id);
        user.getTasks().forEach(task -> taskDao.delete(task.getId()));
        roleDao.deleteAllUserRoles(id);
        super.delete(id);
        profileDao.delete(user.getProfile().getId());
    }

}