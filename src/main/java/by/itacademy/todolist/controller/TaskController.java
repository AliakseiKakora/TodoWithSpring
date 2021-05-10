package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.security.SecurityContextManager;
import by.itacademy.todolist.service.FileService;
import by.itacademy.todolist.service.TaskService;
import by.itacademy.todolist.service.UserService;
import by.itacademy.todolist.util.DateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor

@Controller
@RequestMapping("/task")
public class TaskController {

    private static final String ERROR_TASK_UPDATE_MESSAGE = "task update";
    private static final String ERROR_SEARCH_TASK_MESSAGE = "task search";

    private final TaskService taskService;
    private final FileService fileService;
    private final UserService userService;
    private final DateParser dateParser;
    private final SecurityContextManager securityContextManager;

    @GetMapping
    public ModelAndView loadTaskPage(@RequestParam long taskId, @RequestParam String section) {
        ModelAndView modelAndView = new ModelAndView("taskCard");
        try {
            Task task = taskService.getTaskById(taskId);
            modelAndView.addObject(ApplicationConstants.TASK_KEY, task);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method loadTaskPage", e);
            modelAndView.setViewName("redirect:/tasks/" + section);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, ERROR_SEARCH_TASK_MESSAGE);
            return modelAndView;
        }
    }

    @PostMapping("/add")
    public ModelAndView addTask(HttpServletRequest request, @RequestParam MultipartFile file,
                                @RequestParam String section, @ModelAttribute Task task,
                                @RequestParam(required = false) String date) {
        log.info("user tries to add a task");
        ModelAndView modelAndView = new ModelAndView("redirect:/task/add");
        try {
            long userId = securityContextManager.getUserId();

            User user = userService.getById(userId);
            task.setUser(user);
            task = taskService.saveTaskToSection(task, section, date);

            if (!file.isEmpty()) {
                fileService.addFileInfoForTask(file, task.getId(), userId, request);
            }
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, "task added");

            log.info("user added task {}", task);
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception in addTask method ", e);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, "task adding");
            return modelAndView;
        }
    }

    @PostMapping("/update")
    public ModelAndView updateTask(@ModelAttribute Task task, @RequestParam String date) {
        ModelAndView modelAndView = new ModelAndView("redirect:/task/edit");
        try {
            log.info("user tries update task");
            Task taskForUpdate = taskService.getTaskById(task.getId());
            taskForUpdate.setName(task.getName());
            taskForUpdate.setDescription(task.getDescription());
            taskForUpdate.setDateCompletion(dateParser.getLocalDateTime(date));
            taskService.updateTask(taskForUpdate);

            log.info("the user has successfully updated the task");
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, ApplicationConstants.DATA_UPDATED_MSG);
            modelAndView.addObject(ApplicationConstants.TASK_ID, task.getId());
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in updateTask method", e);
            modelAndView.addObject(ApplicationConstants.TASK_ID, task.getId());
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, ERROR_TASK_UPDATE_MESSAGE);
            return modelAndView;
        }
    }

    @GetMapping("/fix")
    public ModelAndView fixTask(@RequestParam long taskId, @RequestParam String section) {
        return updateTaskStatus(taskId, section, task -> {
            task.setCompleted(true);
            return task;
        });
    }

    @GetMapping("/restore")
    public ModelAndView restoreTask(@RequestParam long taskId, @RequestParam String section) {
        return updateTaskStatus(taskId, section, task -> {
            task.setCompleted(false);
            task.setDeleted(false);
            return task;
        });
    }

    @GetMapping("/delete")
    public ModelAndView deleteTask(@RequestParam long taskId, @RequestParam String section) {
        return updateTaskStatus(taskId, section, task -> {
            task.setDeleted(true);
            return task;
        });
    }

    @GetMapping("/fulDelete")
    public ModelAndView fulDeleteTask(@RequestParam long taskId, @RequestParam String section) {
        log.info("user tries delete task");
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks/" + section);
        try {
            Task task = taskService.getTaskById(taskId);

            if (task.getFileInfo() != null) {
                fileService.delete(task.getFileInfo());
            }
            taskService.deleteTask(task);
            log.info("the user has successfully deleted the task");
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method fulDeleteTask", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, ERROR_TASK_UPDATE_MESSAGE);
            return modelAndView;
        }
    }

    @GetMapping("/clearAll")
    public ModelAndView clearListDeletedTasks() {
        log.info("user tries clear list deleted tasks");
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks/deleted");
        try {
            long userId = securityContextManager.getUserId();

            taskService.deleteAllUserDeletedTask(userId);
            log.info("the user has successfully cleared the list of deleted tasks");
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method clearListDeletedTasks", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, "clear list");
            return modelAndView;
        }
    }

    private ModelAndView updateTaskStatus(@RequestParam long taskId, @RequestParam String section,
                                          Function<Task, Task> function) {
        log.info("user tries update task");
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks/" + section);
        try {
            Task task = taskService.getTaskById(taskId);
            task = function.apply(task);

            taskService.updateTask(task);
            log.info("the user has successfully updated the task");
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method updateTaskStatus", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, ERROR_TASK_UPDATE_MESSAGE);
            return modelAndView;
        }
    }
}