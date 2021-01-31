package by.itacademy.todolist.persistance.mapper.impl;

import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileInfoResultSetMapper implements ResultSetMapper<FileInfo> {

    @Override
    public FileInfo processResultSet(ResultSet rs) throws SQLException {
        return FileInfo.builder()
                .id(rs.getLong("id"))
                .path(rs.getString("path"))
                .build();
    }

}
