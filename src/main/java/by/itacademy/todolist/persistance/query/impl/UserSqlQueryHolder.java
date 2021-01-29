package by.itacademy.todolist.persistance.query.impl;

import by.itacademy.todolist.persistance.query.CrudJdbcSqlQueryHolder;

public class UserSqlQueryHolder implements CrudJdbcSqlQueryHolder {


    private static final String GET_BY_ID_SQL = "select u.id, u.name, u.surname, u.email, p.id as profile_id, p.login, " +
           "p.password, p.profile_enable from users u " +
           "left join profiles p on u.profile_id = p.id" +
           " where u.id = ?";
    private static final String GET_ALL_SQL = "select u.id, u.name, u.surname, u.email, p.id as profile_id, p.login, " +
            "p.password, p.profile_enable from users u " +
            "left join profiles p on u.profile_id = p.id";
    private static final String UPDATE_SQL = "update users set name = ?, surname = ?, email = ? where id = ?";
    private static final String DELETE_SQL = "delete from users where id = ?";
    private static final String CREATE_SQL = "insert into users (name, surname, email, profile_id) values (?,?,?,?)";

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