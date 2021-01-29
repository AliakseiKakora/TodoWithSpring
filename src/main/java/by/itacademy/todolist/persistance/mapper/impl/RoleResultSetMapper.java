package by.itacademy.todolist.persistance.mapper.impl;

import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleResultSetMapper implements ResultSetMapper<Role> {

    @Override
    public Role processResultSet(ResultSet rs) throws SQLException {
        Role role = null;
        switch (rs.getString("role")) {
            case "ADMIN":
                role = Role.ADMIN;
                break;
            case "USER":
                role = Role.USER;
                break;
        }
        return role;
    }

}
