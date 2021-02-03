package by.itacademy.todolist.persistance.dao;

import java.util.List;

public interface TaskDao<Task> extends CrudDao<Task> {

    Task createTaskForUser(Task task, long userId);

    List<Task> getAllUserTasks(long userId);

}
