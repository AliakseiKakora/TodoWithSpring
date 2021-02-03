package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.dao.ProfileDao;
import by.itacademy.todolist.persistance.mapper.impl.ProfileResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.ProfileSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.ProfileStatementInitializer;

public class ProfileJdbcDao extends AbstractJdbcDao<Profile> implements ProfileDao<Profile> {

    public ProfileJdbcDao(Connector connector) {
        super(connector, new ProfileResultSetMapper(), new ProfileSqlQueryHolder(),new ProfileStatementInitializer());
    }

}