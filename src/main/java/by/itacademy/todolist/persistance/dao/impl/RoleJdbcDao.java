package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.exception.DaoException;
import by.itacademy.todolist.persistance.mapper.impl.RoleResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.RoleSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.RoleStatementInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleJdbcDao extends AbstractJdbcDao<Role> {

    private static final String GET_ROLES_BY_ID_SQL = "select r.role from users_roles rs " +
            "left join roles r on r.id = rs.role_id " +
            "where user_id = ?";
    private static final String ADD_USER_ROLES_SQL = "insert into users_roles (user_id, role_id) values (?, ?)";
    private static final String DELETE_ALL_USER_ROLES = "delete from users_roles where user_id = ?";

    public RoleJdbcDao() {
        super(new RoleResultSetMapper(), new RoleSqlQueryHolder(), new RoleStatementInitializer());
    }

    public List<Role> getRolesByUserId(long id) {
        try (Connection connection = getConnector().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(GET_ROLES_BY_ID_SQL)) {

            pStatement.setLong(1, id);

            try (ResultSet resultSet = pStatement.executeQuery()) {
                List<Role> roles = new ArrayList<>();
                while (resultSet.next()) {
                    Role role = getResultSetMapper().processResultSet(resultSet);
                    roles.add(role);
                }
                return roles;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error in method getRolesByUserId witch id - " + id + " " + e.getMessage());
                throw new DaoException("Error in method getRolesByUserId witch id - " + id + " " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error receive database connection: " + e.getMessage());
            throw new DaoException("Error receive database connection: " + e.getMessage());
        }
    }

    public void addUserRoles(User user) {
        List<Role> roles = user.getRoles();

        try (Connection connection = getConnector().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(ADD_USER_ROLES_SQL)) {

            connection.setAutoCommit(false);

            for (Role role : roles) {
                pStatement.setLong(1, user.getId());
                pStatement.setLong(2, role.getId());
                pStatement.addBatch();
            }
            pStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in method addUserRoles + " + e.getMessage());
            throw new DaoException("Error in method addUserRoles + " + e.getMessage());
        }
    }

    public void deleteAllUserRoles(long userId) {
        try (Connection connection = getConnector().getConnection();
             PreparedStatement pStatement = connection.prepareStatement(DELETE_ALL_USER_ROLES)) {

            pStatement.setLong(1, userId);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in method deleteAllUserRoles " + e.getMessage());
            throw new DaoException("Error in method deleteAllUserRoles " + e.getMessage());
        }
    }

}