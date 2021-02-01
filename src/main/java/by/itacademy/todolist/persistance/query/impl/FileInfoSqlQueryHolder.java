package by.itacademy.todolist.persistance.query.impl;

import by.itacademy.todolist.persistance.query.CrudJdbcSqlQueryHolder;

public class FileInfoSqlQueryHolder implements CrudJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "select id, path from files_info where id = ?";
    private static final String GET_ALL_SQL = "select id, path from files_info";
    private static final String UPDATE_SQL = "update files_info set path = ? where id = ?";
    private static final String DELETE_SQL = "delete from files_info where id = ?";
    private static final String CREATE_SQL = "insert into files_info (path) values (?)";

    @Override
    public String getByIdSql() {
        return GET_BY_ID_SQL;
    }

    @Override
    public String getAllSql() {
        return GET_ALL_SQL;
    }

    @Override
    public String getUpdateSql() {
        return UPDATE_SQL;
    }

    @Override
    public String getDeleteSql() {
        return DELETE_SQL;
    }

    @Override
    public String getCreateSql() {
        return CREATE_SQL;
    }

}