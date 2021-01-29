package by.itacademy.todolist.persistance.statement.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileStatementInitializer implements StatementInitializer<Profile> {

    @Override
    public void processCreateStatement(PreparedStatement pStatement, Profile profile) throws SQLException {
        pStatement.setString(1, profile.getLogin());
        pStatement.setString(2, profile.getPassword());
        pStatement.setBoolean(3, profile.isEnable());

    }

    @Override
    public long processUpdateStatement(PreparedStatement pStatement, Profile profile) throws SQLException {
        processCreateStatement(pStatement, profile);
        pStatement.setLong(4, profile.getId());
        return profile.getId();
    }

}
