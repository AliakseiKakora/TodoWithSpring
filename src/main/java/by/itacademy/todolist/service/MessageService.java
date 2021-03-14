package by.itacademy.todolist.service;

import by.itacademy.todolist.model.Message;

import java.util.List;

public interface MessageService {

    Message getById(long id);

    List<Message> getAll();

    Message create(Message message);

}