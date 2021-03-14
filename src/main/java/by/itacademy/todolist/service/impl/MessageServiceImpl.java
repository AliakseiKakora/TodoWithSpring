package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.persistance.dao.MessageDao;
import by.itacademy.todolist.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    private final MessageDao<Message> messageDao;

    public MessageServiceImpl(MessageDao<Message> messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Message getById(long id) {
        return messageDao.getById(id);
    }

    @Override
    public List<Message> getAll() {
        return messageDao.getAll();
    }

    @Override
    public Message create(Message message) {
        return messageDao.create(message);
    }

    @Override
    public void delete(long id) {
        messageDao.delete(id);
    }
}