package by.itacademy.todolist.persistance.mapper.impl;

import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleResultSetMapper implements ResultSetMapper<Role> {

    @Override
    public Role processResultSet(ResultSet rs) throws SQLException {
        return Role.builder()
                .id(rs.getLong("id"))
                .role(rs.getString("role"))
                .build();
    }

}
