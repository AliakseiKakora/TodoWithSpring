package by.itacademy.todolist.persistence.dao;

public interface MessageDao<Message> extends CrudDao<Message> {

    void deleteAllUserMessage(long userId);

}
