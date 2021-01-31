package by.itacademy.todolist.persistance.statement.impl;

import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.persistance.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FileInfoStatementInitializer implements StatementInitializer<FileInfo> {

    @Override
    public void processCreateStatement(PreparedStatement pStatement, FileInfo fileInfo) throws SQLException {
        pStatement.setString(1, fileInfo.getPath());
    }

    @Override
    public long processUpdateStatement(PreparedStatement pStatement, FileInfo fileInfo) throws SQLException {
        pStatement.setString(1, fileInfo.getPath());
        pStatement.setLong(2, fileInfo.getId());
        return fileInfo.getId();
    }

}