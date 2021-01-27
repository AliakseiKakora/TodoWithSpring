package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.mapper.impl.UserResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.UserSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.UserStatementInitializer;

public class UserJdbcDao extends AbstractJdbcDao<User> {

    public UserJdbcDao() {
        super(new UserResultSetMapper(), new UserSqlQueryHolder(), new UserStatementInitializer());
    }

}