package by.itacademy.todolist.persistance.statement.impl;

import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserStatementInitializer implements StatementInitializer<User> {

    @Override
    public void processCreateStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
    }

    @Override
    public long processUpdateStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        processCreateStatement(preparedStatement, user);
        preparedStatement.setLong(4, user.getId());
        return user.getId();
    }

}
