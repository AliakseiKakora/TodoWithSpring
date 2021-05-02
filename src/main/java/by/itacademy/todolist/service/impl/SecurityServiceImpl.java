package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public void checkRightToTask(Task task, User user) {
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("User not have right to current task");
        }
    }
}