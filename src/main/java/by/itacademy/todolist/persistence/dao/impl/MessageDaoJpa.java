package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.persistence.dao.MessageDao;

public class MessageDaoJpa extends AbstractDaoJpa<Message> implements MessageDao<Message> {

    //todo it need for jdbc???
    @Override
    public void deleteAllUserMessage(long userId) {

    }
}
