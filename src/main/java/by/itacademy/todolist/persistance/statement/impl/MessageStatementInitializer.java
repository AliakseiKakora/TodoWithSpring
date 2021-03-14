package by.itacademy.todolist.persistance.statement.impl;

import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MessageStatementInitializer implements StatementInitializer<Message> {

    @Override
    public void processCreateStatement(PreparedStatement pStatement, Message message) throws SQLException {
        Timestamp dateAdded = Timestamp.valueOf(message.getDateAdded());
        pStatement.setString(1, message.getMessage());
        pStatement.setTimestamp(2, dateAdded);
        pStatement.setLong(3, message.getUser().getId());
    }

    @Override
    public long processUpdateStatement(PreparedStatement pStatement, Message message) throws SQLException {
        Timestamp dateAdded = Timestamp.valueOf(message.getDateAdded());
        pStatement.setString(1, message.getMessage());
        pStatement.setTimestamp(2, dateAdded);
        pStatement.setLong(3, message.getId());
        return message.getId();
    }
}
