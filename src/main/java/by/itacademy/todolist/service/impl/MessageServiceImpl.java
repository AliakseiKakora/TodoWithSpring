package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.persistence.MessageRepository;
import by.itacademy.todolist.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message getById(long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("message with id " + id + " not found"));
    }

    @Override
    public List<Message> getAll() {
        return (List<Message>) messageRepository.findAll();
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(long id) {
        messageRepository.deleteById(id);
    }

}