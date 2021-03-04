package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.dao.*;
import by.itacademy.todolist.persistance.exception.DaoException;
import by.itacademy.todolist.persistance.mapper.impl.UserResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.UserSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.UserStatementInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJdbcDao extends AbstractJdbcDao<User> implements UserDao<User> {

    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "select u.id, u.name, u.surname, u.email, p.id as profile_id, p.login, " +
            "p.password, p.profile_enable from users u " +
            "left join profiles p on u.profile_id = p.id" +
            " where p.login = ? and p.password = ?";

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
        roleDao.deleteAllUserRoles(id);
        super.delete(id);
        profileDao.delete(user.getProfile().getId());
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        try (Connection connection = getConnector().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            pStatement.setString(1, login);
            pStatement.setString(2, password);

            try (ResultSet resultSet = pStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = getResultSetMapper().processResultSet(resultSet);
                    user.setRoles(roleDao.getRolesByUserId(user.getId()));
                    return user;
                }
                throw new DaoException("Invalid user data");

            }  catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error in method getUserByLoginAndPassword " + e.getMessage());
                throw new DaoException("Error in method getUserByLoginAndPassword " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error receive database connection: " + e.getMessage());
            throw new DaoException("Error receive database connection: " + e.getMessage());
        }
    }
}