package by.itacademy.todolist.persistance.dao;

public interface MessageDao<Message> extends CrudDao<Message> {

    void deleteAllUserMessage(long userId);

}
