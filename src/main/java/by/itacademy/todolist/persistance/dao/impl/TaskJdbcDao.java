package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.mapper.impl.TaskResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.TaskSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.TaskStatementInitializer;

public class TaskJdbcDao extends AbstractJdbcDao<Task> {

    public TaskJdbcDao() {
        super(new TaskResultSetMapper(), new TaskSqlQueryHolder(), new TaskStatementInitializer());
    }

}
