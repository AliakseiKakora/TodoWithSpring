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
    public List<Task> getTodayUserTasks(User user) {
        return user.getTasks().stream().filter(this::isTodayOrBeforeTask).collect(Collectors.toList());
    }

    @Override
    public List<Task> getTomorrowUserTasks(User user) {
        return user.getTasks().stream().filter(this::isTomorrowTask).collect(Collectors.toList());
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

    private boolean isSomeDayTask(Task task) {
        LocalDateTime dateCompletion = task.getDateCompletion();
        LocalDateTime now = LocalDateTime.now().plusDays(1);
        return now.getYear() == dateCompletion.getYear()
                && now.getDayOfYear() < dateCompletion.getDayOfYear();
    }
//    public static void main(String[] args) {
//        LocalDateTime taskTime = LocalDateTime.of(2021, 2, 25, 12, 0);
//
//        Task task = Task.builder().dateCompletion(taskTime).build();
//        System.out.println(isSomeDayTask(task));
//
//    }

    @Override
    public List<Task> getSomeDayUserTasks(User user) {
        return user.getTasks().stream().filter(this::isSomeDayTask).collect(Collectors.toList());
    }

    @Override
    public List<Task> getFixedUserTasks(User user) {
        return user.getTasks().stream().filter(Task::isCompleted).collect(Collectors.toList());
    }

    @Override
    public List<Task> getDeletedUserTasks(User user) {
        return user.getTasks().stream().filter(Task::isDeleted).collect(Collectors.toList());
    }
}
