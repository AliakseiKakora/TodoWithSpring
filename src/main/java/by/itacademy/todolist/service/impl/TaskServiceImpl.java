package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistance.dao.TaskDao;
import by.itacademy.todolist.service.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {

    private TaskDao<Task> taskDao;

    public TaskServiceImpl(TaskDao<Task> taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public List<Task> getAllUserTasks(long userId) {
        return taskDao.getAllUserTasks(userId);
    }

    @Override
    public List<Task> getTodayUserTasks(long userId) {
        List<Task> tasks = getAllUserTasks(userId);
        return tasks.stream()
                .filter(task -> !task.isCompleted() && !task.isDeleted())
                .filter(this::isTodayOrBeforeTask)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTomorrowUserTasks(long userId) {
        List<Task> tasks = getAllUserTasks(userId);
        return tasks.stream()
                .filter(task -> !task.isCompleted() && !task.isDeleted())
                .filter(this::isTomorrowTask)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getSomeDayUserTasks(long userId) {
        List<Task> tasks = getAllUserTasks(userId);
        return tasks.stream()
                .filter(task -> !task.isCompleted() && !task.isDeleted())
                .filter(this::isSomeDayTask)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getFixedUserTasks(long userId) {
        List<Task> tasks = getAllUserTasks(userId);
        return tasks.stream()
                .filter(task -> !task.isDeleted())
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getDeletedUserTasks(long userId) {
        List<Task> tasks = getAllUserTasks(userId);
        return tasks.stream().filter(Task::isDeleted).collect(Collectors.toList());
    }

    @Override
    public Task createTaskForUser(Task task, long userId) {
        return taskDao.createTaskForUser(task, userId);
    }

    private boolean isTodayOrBeforeTask(Task task) {
        LocalDateTime dateCompletion = task.getDateCompletion();
        LocalDateTime now = LocalDateTime.now();
        return (now.getYear() == dateCompletion.getYear()
                && now.getDayOfYear() == dateCompletion.getDayOfYear())
                || now.isAfter(dateCompletion);
    }

    private boolean isTomorrowTask(Task task) {
        LocalDateTime dateCompletion = task.getDateCompletion();
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        return now.getYear() == dateCompletion.getYear()
                && now.getDayOfYear() == dateCompletion.getDayOfYear();
    }

//    private boolean isSomeDayTask12(Task task) {
//        LocalDateTime dateCompletion = task.getDateCompletion();
//        LocalDateTime now = LocalDateTime.now().plusDays(1);
//        return now.getYear() == dateCompletion.getYear()
//                && now.getDayOfYear() < dateCompletion.getDayOfYear();
//    }

    private boolean isSomeDayTask(Task task) {
        LocalDateTime dateCompletion = task.getDateCompletion();
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        if (dateCompletion.getYear() > now.getYear()) {
            return true;
        }
        return dateCompletion.getYear() == now.getYear()
                && dateCompletion.getDayOfYear() > now.getDayOfYear();
    }

    @Override
    public Task updateTask(Task task) {
        return taskDao.update(task);
    }

    @Override
    public Task getTaskById(long id) {
        return taskDao.getById(id);
    }
}
