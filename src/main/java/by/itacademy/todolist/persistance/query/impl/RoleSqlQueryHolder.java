package by.itacademy.todolist.persistance.query.impl;

import by.itacademy.todolist.persistance.query.CrudJdbcSqlQueryHolder;

public class RoleSqlQueryHolder implements CrudJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "select id, role from roles where id = ?";
    private static final String GET_ALL_SQL = "select id, role from roles";
    private static final String UPDATE_SQL = "update roles set role = ? where id = ?";
    private static final String DELETE_SQL = "delete from roles where id = ?";
    private static final String CREATE_SQL = "insert into roles (role) values (?)";

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