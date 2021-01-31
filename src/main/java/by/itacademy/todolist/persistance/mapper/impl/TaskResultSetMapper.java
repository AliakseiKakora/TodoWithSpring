package by.itacademy.todolist.persistance.mapper.impl;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskResultSetMapper implements ResultSetMapper<Task> {

    @Override
    public Task processResultSet(ResultSet rs) throws SQLException {

        return Task.builder()
                .id(rs.getLong("id"))
                .description(rs.getString("description"))
                .dateAdded(rs.getTimestamp("date_added").toLocalDateTime())
                .dateCompletion(rs.getTimestamp("date_completion").toLocalDateTime())
                .isCompleted(rs.getBoolean("completed"))
                .isDeleted(rs.getBoolean("deleted"))
                .build();

    }
}
