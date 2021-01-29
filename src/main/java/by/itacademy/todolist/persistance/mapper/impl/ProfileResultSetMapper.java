package by.itacademy.todolist.persistance.mapper.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileResultSetMapper implements ResultSetMapper<Profile> {

    @Override
    public Profile processResultSet(ResultSet rs) throws SQLException {
        return Profile.builder()
                .id(rs.getLong("id"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .isEnable(rs.getBoolean("profile_enable"))
                .build();
    }

}
