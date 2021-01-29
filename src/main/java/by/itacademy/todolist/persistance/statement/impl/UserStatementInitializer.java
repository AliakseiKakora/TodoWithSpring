package by.itacademy.todolist.persistance.statement.impl;

import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserStatementInitializer implements StatementInitializer<User> {

    @Override
    public void processCreateStatement(PreparedStatement pStatement, User user) throws SQLException {
        pStatement.setString(1, user.getName());
        pStatement.setString(2, user.getSurname());
        pStatement.setString(3, user.getEmail());
        pStatement.setLong(4, user.getProfile().getId());
    }

    @Override
    public long processUpdateStatement(PreparedStatement pStatement, User user) throws SQLException {
        pStatement.setString(1, user.getName());
        pStatement.setString(2, user.getSurname());
        pStatement.setString(3, user.getEmail());
        pStatement.setLong(4, user.getId());
        return user.getId();
    }

}
