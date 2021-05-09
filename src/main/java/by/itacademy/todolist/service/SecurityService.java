package by.itacademy.todolist.service;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;

public interface SecurityService {

    void checkRightToTask(Task task, User user);

//    void checkAdminRole(User user);

}