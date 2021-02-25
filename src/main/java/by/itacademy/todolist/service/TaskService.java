package by.itacademy.todolist.service;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;

import java.util.List;

public interface TaskService {

    List<Task> getAllUserTasks(long userId);

    List<Task> getTodayUserTasks(User user);

    List<Task> getTomorrowUserTasks(User user);

    List<Task> getSomeDayUserTasks(User user);

    List<Task> getFixedUserTasks(User user);

    List<Task> getDeletedUserTasks(User user);

}
