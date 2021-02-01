package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.dao.UserDao;
import by.itacademy.todolist.persistance.mapper.impl.UserResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.UserSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.UserStatementInitializer;

import java.util.List;

public class UserJdbcDao extends AbstractJdbcDao<User> implements UserDao {

    private static final String CREATE_PROFILE_SQL = "insert into profiles (login, password, profile_enable) values (?,?,?)";
    private static final String CREATE_USER_SQL = "insert into users (name, surname, email, profile_id) values (?,?,?,?)";
    private static final String ADD_USER_ROLES_SQL = "insert into users_roles (user_id, role_id) values (?, ?)";

    private ProfileJdbcDao profileJdbcDao = new ProfileJdbcDao();
    private RoleJdbcDao roleJdbcDao = new RoleJdbcDao();
    private TaskJdbcDao taskJdbcDao = new TaskJdbcDao();

    public UserJdbcDao() {
        super(new UserResultSetMapper(), new UserSqlQueryHolder(), new UserStatementInitializer());
    }

    @Override
    public User create(User user) {
        Profile createdProfile = profileJdbcDao.create(user.getProfile());

        user.setProfile(createdProfile);
        User createdUser = super.create(user);
        createdUser.setRoles(user.getRoles());
        roleJdbcDao.addUserRoles(createdUser);
        return createdUser;
    }

    @Override
    public User getById(long id) {
        List<Role> usersRoles = roleJdbcDao.getRolesByUserId(id);
        List<Task> userTask = taskJdbcDao.getAllUserTasks(id);
        User user = super.getById(id);
        user.setRoles(usersRoles);
        user.setTasks(userTask);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = super.getAll();
        users.forEach(u -> u.setRoles(roleJdbcDao.getRolesByUserId(u.getId())));
        users.forEach(u -> u.setTasks(taskJdbcDao.getAllUserTasks(u.getId())));
        return users;
    }

    @Override
    public void delete(long id) {
        User user = getById(id);
        user.getTasks().forEach(task -> taskJdbcDao.delete(task.getId()));
        roleJdbcDao.deleteAllUserRoles(id);
        super.delete(id);
        profileJdbcDao.delete(user.getProfile().getId());
    }

}