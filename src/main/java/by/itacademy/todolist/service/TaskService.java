package by.itacademy.todolist.service;

import by.itacademy.todolist.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllUserTasks(long userId);

    List<Task> getTodayUserTasks(long userId);

    List<Task> getTomorrowUserTasks(long userId);

    List<Task> getSomeDayUserTasks(long userId);

    List<Task> getFixedUserTasks(long userId);

    List<Task> getDeletedUserTasks(long userId);

    Task saveTaskToSection(Task task, String section, String date);

    Task updateTask(Task task);

    Task getTaskById(long id);

    void deleteTask(long id);

    void deleteAllUserDeletedTask(long userId);

    void deleteAllUserTasks(long userId);

}
