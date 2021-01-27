package by.itacademy.todolist.persistance.mapper.impl;

import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetMapper implements ResultSetMapper<User> {

    @Override
    public User processResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .email(rs.getString("email"))
                .build();
    }

}
