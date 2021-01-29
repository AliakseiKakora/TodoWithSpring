package by.itacademy.todolist.persistance.mapper.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetMapper implements ResultSetMapper<User> {

    @Override
    public User processResultSet(ResultSet rs) throws SQLException {
        Profile profile = Profile.builder()
                .id(rs.getLong("profile_id"))
                .login(rs.getString("login"))
                .password(rs.getString("password"))
                .isEnable(rs.getBoolean("profile_enable"))
                .build();
        return User.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .email(rs.getString("email"))
                .profile(profile)
                .build();
    }

}
