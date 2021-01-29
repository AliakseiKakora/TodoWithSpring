package by.itacademy.todolist.persistance.statement.impl;

import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RoleStatementInitializer implements StatementInitializer<Role> {

    @Override
    public void processCreateStatement(PreparedStatement pStatement, Role role) throws SQLException {
        pStatement.setLong(1, role.getId());
        pStatement.setString(2, role.getRole());
    }

    @Override
    public long processUpdateStatement(PreparedStatement pStatement, Role role) throws SQLException {
        pStatement.setString(1, role.getRole());
        pStatement.setLong(2, role.getId());
        return role.getId();
    }

}