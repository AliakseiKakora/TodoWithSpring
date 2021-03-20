package by.itacademy.todolist.persistence.dao;

import java.util.List;

public interface TaskDao<Task> extends CrudDao<Task> {

    Task createTaskForUser(Task task, long userId);

    List<Task> getAllUserTasks(long userId);

    void deleteAllUserTasks(long userId);

}