package by.itacademy.todolist.persistance.statement.impl;

import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleStatementInitializer implements StatementInitializer<Role> {

    @Override
    public void processCreateStatement(PreparedStatement preparedStatement, Role role) throws SQLException {
        preparedStatement.setString(1, role.getRole());
    }

    @Override
    public long processUpdateStatement(PreparedStatement preparedStatement, Role role) throws SQLException {
        preparedStatement.setString(1, role.getRole());
        preparedStatement.setLong(2, role.getId());
        return role.getId();
    }

}