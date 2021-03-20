package by.itacademy.todolist.persistence.dao;

import java.util.List;

public interface TaskDao<Task> extends CrudDao<Task> {

    List<Task> getAllUserTasks(long userId);

    void deleteAllUserTasks(long userId);

    void deleteUserTasksMarkedAsDeleted(long userId);

}