package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.mapper.ResultSetMapper;
import by.itacademy.todolist.persistance.mapper.impl.UserResultSetMapper;
import by.itacademy.todolist.persistance.query.CrudJdbcSqlQueryHolder;
import by.itacademy.todolist.persistance.query.impl.UserSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.StatementInitializer;
import by.itacademy.todolist.persistance.statement.impl.UserStatementInitializer;

public class UserJdbcDao extends AbstractJdbcDao<User> {

    private final ResultSetMapper<User> resultSetMapper;
    private final CrudJdbcSqlQueryHolder queryHolder;
    private final StatementInitializer<User> statementInitializer;

    public UserJdbcDao() {
        this.resultSetMapper = new UserResultSetMapper();
        this.queryHolder = new UserSqlQueryHolder();
        this.statementInitializer = new UserStatementInitializer();
    }

    @Override
    protected CrudJdbcSqlQueryHolder getQueryHolder() {
        return queryHolder;
    }

    @Override
    protected StatementInitializer<User> getStatementInitializer() {
        return statementInitializer;
    }

    @Override
    protected ResultSetMapper<User> getResultSetMapper() {
        return resultSetMapper;
    }

}