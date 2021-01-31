package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.mapper.impl.FileInfoResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.FileInfoSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.FileInfoStatementInitializer;

public class FileInfoJdbcDao extends AbstractJdbcDao<FileInfo> {

    public FileInfoJdbcDao() {
        super(new FileInfoResultSetMapper(), new FileInfoSqlQueryHolder(), new FileInfoStatementInitializer());
    }

}
