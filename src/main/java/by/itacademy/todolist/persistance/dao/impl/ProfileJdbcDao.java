package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.dao.ProfileDao;
import by.itacademy.todolist.persistance.exception.DaoException;
import by.itacademy.todolist.persistance.mapper.impl.ProfileResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.ProfileSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.ProfileStatementInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileJdbcDao extends AbstractJdbcDao<Profile> implements ProfileDao<Profile> {

    private static final String EXIST_LOGIN_AND_EMAIL = "select exists (select u.id from users u " +
            "left join profiles p on u.profile_id = p.id " +
            "where login = ? or email = ?)";

    public ProfileJdbcDao(Connector connector) {
        super(connector, new ProfileResultSetMapper(), new ProfileSqlQueryHolder(),new ProfileStatementInitializer());
    }

    @Override
    public boolean existLoginAndEmail(String login, String email) {
        try(Connection connection = getConnector().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(EXIST_LOGIN_AND_EMAIL)) {

            pStatement.setString(1, login);
            pStatement.setString(2, email);

            try (ResultSet resultSet = pStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1);
                }
                throw new DaoException("Error in method existLoginAndPassword");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error in method existLoginAndPassword " + e.getMessage());
                throw new DaoException("Error in method existLoginAndPassword " + e.getMessage());
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error receive database connection: " + e.getMessage());
            throw new DaoException("Error receive database connection: " + e.getMessage());
        }
    }

}