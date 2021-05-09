package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistence.TaskRepository;
import by.itacademy.todolist.service.FileService;
import by.itacademy.todolist.service.TaskService;
import by.itacademy.todolist.util.DateParser;
import by.itacademy.todolist.util.TaskPredicateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final FileService fileService;
    private final DateParser dateParser;
    private final TaskPredicateManager predicateManager;

    @Override
    public List<Task> getAllUserTasks(long userId) {
        return taskRepository.findByUserId(userId);
    }

    @PreAuthorize("#userId == authentication.principal.id")
    public List<Task> getUserTasksBySection(long userId, String section) {
        List<Task> tasks = getAllUserTasks(userId);
        return tasks.stream().filter(predicateManager.getPredicateBySection(section))
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("#task.user.id == authentication.principal.id")
    public Task saveTaskToSection(Task task, String section, String date) {
        if (task.getName() == null || task.getName().equals("")) {
            throw new RuntimeException("Task name cannot be empty");
        }
        setDateForTask(task, section, date);

        if (task.getDateCompletion() == null || task.getDateAdded() == null) {
            throw new RuntimeException("Task date cannot be empty");
        }
        return taskRepository.save(task);
    }

    private void setDateForTask(Task task, String section, String date) {
        switch (section) {
            case ApplicationConstants.SECTION_SOME_DAY:
                LocalDateTime timeCompleted = dateParser.getLocalDateTime(date);
                task.setDateCompletion(timeCompleted);
                break;
            case ApplicationConstants.SECTION_TODAY:
                task.setDateCompletion(dateParser.getTodayLocalDateTime());
                break;
            case ApplicationConstants.SECTION_TOMORROW:
                task.setDateCompletion(dateParser.getTomorrowLocalDateTime());
                break;
        }
    }

    @Override
    @PreAuthorize("#task.user.id == authentication.principal.id")
    public Task updateTask(Task task) {
        if (task.getName() == null || task.getName().equals("") || task.getDateCompletion() == null) {
            throw new RuntimeException("Task name or date completion cannot be empty");
        }
        return taskRepository.save(task);
    }

    @Override
    @PostAuthorize("returnObject.user.id == authentication.principal.id")
    public Task getTaskById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("task with id " + id + " not found"));
    }

    @Override
    @PreAuthorize("#task.user.id == authentication.principal.id")
    public void deleteTask(Task task) {
        taskRepository.deleteById(task.getId());
    }

    @Override
    @PreAuthorize("#userId == authentication.principal.id")
    public void deleteAllUserDeletedTask(long userId) {
        List<Task> deletedTasks = getAllUserTasks(userId).stream()
                .filter(Task::isDeleted).collect(Collectors.toList());

        deletedTasks.stream().filter(task -> task.getFileInfo() != null)
                .forEach(task -> fileService.delete(task.getFileInfo()));

        taskRepository.deleteUserTasksMarkedAsDeleted(userId);
    }

    @Override
    public void deleteAllUserTasks(long userId) {
        List<Task> allUserTasks = getAllUserTasks(userId);
        if (allUserTasks.isEmpty()) {
            return;
        }

        allUserTasks.stream().filter(task -> task.getFileInfo() != null)
                .forEach(task -> fileService.delete(task.getFileInfo()));
        taskRepository.deleteTasksByUserId(userId);
    }
}