package by.itacademy.todolist.persistance.dao.impl;

import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.dao.AbstractJdbcDao;
import by.itacademy.todolist.persistance.dao.MessageDao;
import by.itacademy.todolist.persistance.mapper.impl.MessageResultSetMapper;
import by.itacademy.todolist.persistance.query.impl.MessageSqlQueryHolder;
import by.itacademy.todolist.persistance.statement.impl.MessageStatementInitializer;

public class MessageJdbcDao extends AbstractJdbcDao<Message> implements MessageDao<Message> {


    public MessageJdbcDao(Connector connector) {
        super(connector, new MessageResultSetMapper(), new MessageSqlQueryHolder(), new MessageStatementInitializer());
    }
}
