package by.itacademy.todolist.persistance.dao;

import java.util.List;

public interface TaskDao<Task> {

    Task createTaskForUser(Task task, long userId);

    List<Task> getAllUserTasks(long userId);

    void deleteAllUserTasks(long userId);

}
