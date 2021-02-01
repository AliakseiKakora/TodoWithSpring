package by.itacademy.todolist.persistance.query.impl;

import by.itacademy.todolist.persistance.query.CrudJdbcSqlQueryHolder;

public class TaskSqlQueryHolder implements CrudJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "select t.id, t.description, t.date_added, t.date_completion, t.completed," +
           " t.deleted, fi.id as file_id, fi.path from tasks t left join files_info fi on fi.task_id = t.id where t.id = ?";
    private static final String GET_ALL_SQL = "select t.id, t.description, t.date_added, t.date_completion, t.completed," +
            " t.deleted, fi.id as file_id, fi.path from tasks t left join files_info fi on fi.task_id = t.id";
    private static final String UPDATE_SQL = "update tasks set description = ?, date_added = ?, date_completion = ?, " +
            "completed = ?, deleted = ? where id = ?";
    private static final String DELETE_SQL = "delete from tasks where id = ?";
    private static final String CREATE_SQL = "insert into tasks (description, date_added, date_completion, completed, deleted)" +
            " values (?,?,?,?,?)";

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
