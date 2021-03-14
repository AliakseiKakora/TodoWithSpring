package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.dao.MessageDao;
import by.itacademy.todolist.persistance.exception.DaoException;
import by.itacademy.todolist.persistance.mapper.impl.MessageResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.MessageSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.MessageStatementInitializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageJdbcDao extends AbstractJdbcDao<Message> implements MessageDao<Message> {

    private static final String DELETE_ALL_USER_MESSAGE = "delete from messages where user_id = ?";


    public MessageJdbcDao(Connector connector) {
        super(connector, new MessageResultSetMapper(), new MessageSqlQueryHolder(), new MessageStatementInitializer());
    }

    @Override
    public void deleteAllUserMessage(long userId) {
        try(Connection connection = getConnector().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DELETE_ALL_USER_MESSAGE)) {

            pStatement.setLong(1, userId);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in method deleteAllUserMessages " + e.getMessage());
            throw new DaoException("Error in method deleteAllUserMessages " + e.getMessage());
        }
    }
}