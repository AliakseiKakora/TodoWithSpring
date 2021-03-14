package by.itacademy.todolist.persistance.mapper.impl;

import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageResultSetMapper implements ResultSetMapper<Message> {

    @Override
    public Message processResultSet(ResultSet rs) throws SQLException {
        User user = User.builder().
                id(rs.getLong("user_id")).
                name(rs.getString("name")).
                surname(rs.getString("surname")).
                email(rs.getString("email")).
                build();

        return Message.builder().
                id(rs.getLong("id")).
                message(rs.getString("message")).
                dateAdded(rs.getTimestamp("date_added").toLocalDateTime()).
                user(user).
                build();
    }
}