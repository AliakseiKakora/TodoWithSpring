package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.mapper.impl.RoleResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.RoleSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.RoleStatementInitializer;

public class RoleJdbcDao extends AbstractJdbcDao<Role> {

    public RoleJdbcDao() {
        super(new RoleResultSetMapper(), new RoleSqlQueryHolder(), new RoleStatementInitializer());
    }

}