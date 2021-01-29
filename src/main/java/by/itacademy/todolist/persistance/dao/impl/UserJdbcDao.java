package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.exception.DaoException;
import by.itacademy.todolist.persistance.mapper.impl.UserResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.UserSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.UserStatementInitializer;

import java.sql.*;
import java.util.List;

public class UserJdbcDao extends AbstractJdbcDao<User> {

    private static final String CREATE_PROFILE_SQL = "insert into profiles (login, password, profile_enable) values (?,?,?)";
    private static final String CREATE_USER_SQL = "insert into users (name, surname, email, profile_id) values (?,?,?,?)";
    private static final String ADD_USER_ROLES_SQL = "insert into users_roles (user_id, role_id) values (?, ?)";

    private ProfileJdbcDao profileJdbcDao = new ProfileJdbcDao();
    private RoleJdbcDao roleJdbcDao = new RoleJdbcDao();

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
    
    public User createWitchTransaction(User user) {
        long profileId;
        long userId = 0;
        Connection connection = getConnector().getConnection();
        try {
            connection.setAutoCommit(false);

            try (PreparedStatement profileStatement = connection.prepareStatement(CREATE_PROFILE_SQL,
                    Statement.RETURN_GENERATED_KEYS)) {

                profileStatement.setString(1, user.getProfile().getLogin());
                profileStatement.setString(2, user.getProfile().getPassword());
                profileStatement.setBoolean(3, user.getProfile().isEnable());
                profileStatement.executeUpdate();


                try (ResultSet resultSet = profileStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        profileId = resultSet.getLong(1);
                    } else {
                        System.out.println("Error in method create user. Error witch generated id");
                        throw new DaoException("Error in method create user. Error witch generated id");
                    }
                }
            }
            try (PreparedStatement userStatement = connection.prepareStatement(CREATE_USER_SQL,
                    Statement.RETURN_GENERATED_KEYS)) {

                userStatement.setString(1, user.getName());
                userStatement.setString(2, user.getSurname());
                userStatement.setString(3, user.getEmail());
                userStatement.setLong(4, profileId);
                userStatement.executeUpdate();

                try (ResultSet resultSet = userStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        userId = resultSet.getLong(1);
                    } else {
                        System.out.println("Error in method create user. Error witch generated id");
                        throw new DaoException("Error in method create user. Error witch generated id");
                    }
                }

            }

            try (PreparedStatement roleStatement = connection.prepareStatement(ADD_USER_ROLES_SQL)) {
                for (Role role: user.getRoles()) {
                    roleStatement.setLong(1, userId);
                    roleStatement.setLong(2, role.getId());
                    roleStatement.addBatch();
                }
                roleStatement.executeBatch();
            }
            connection.commit();


        } catch (SQLException e) {
            System.out.println("Error in method create user. The transaction must be rollback." + e.getMessage());
            try {
                connection.rollback();
                throw new DaoException("Error in method create user. The transaction must be rollback." + e.getMessage());
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Error in method create user. Error rollback transaction.");
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // NOP
            }
        }
        return getById(userId);
    }

    @Override
    public User getById(long id) {
        List<Role> usersRoles = roleJdbcDao.getRolesByUserId(id);
        User user = super.getById(id);
        user.setRoles(usersRoles);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = super.getAll();
        users.forEach(u -> u.setRoles(roleJdbcDao.getRolesByUserId(u.getId())));
        return users;
    }

    @Override
    public void delete(long id) {
        User user = super.getById(id);
        roleJdbcDao.deleteAllUserRoles(id);
        super.delete(id);
        profileJdbcDao.delete(user.getProfile().getId());
    }

}