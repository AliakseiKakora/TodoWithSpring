package by.itacademy.todolist.persistance.query.impl;

import by.itacademy.todolist.persistance.query.CrudJdbcSqlQueryHolder;

public class MessageSqlQueryHolder implements CrudJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "select m.id, m.message, m.date_added, u.id as user_id, u.name, u.surname, u.email " +
            "from messages m left join users u on m.user_id = u.id " +
            "where m.id = ?";
    private static final String GET_ALL_SQL = "select m.id, m.message, m.date_added, u.id as user_id, u.name, u.surname, u.email " +
            "from messages m left join users u on m.user_id = u.id";
    private static final String UPDATE_SQL = "update messages set message = ?, date_added = ? where id = ?";
    private static final String DELETE_SQL = "delete from messages where id = ?";
    private static final String CREATE_SQL = "insert into messages (message, date_added, user_id) values (?,?,?)";

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
