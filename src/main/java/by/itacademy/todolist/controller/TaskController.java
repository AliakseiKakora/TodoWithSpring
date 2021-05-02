package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.FileService;
import by.itacademy.todolist.service.TaskService;
import by.itacademy.todolist.util.DateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private final DateParser dateParser;

    @PostMapping
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
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            task.setUser(user);
            task = taskService.saveTaskToSection(task, section, date);

            if (!file.isEmpty()) {
                fileService.addFileInfoForTask(file, task.getId(), user.getId(), getPath(request));
            }

            ModelAndView modelAndView = new ModelAndView("redirect:/task/add");
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, "task added");

            log.info("user added task {}", task);
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception in addTask method ", e);
            ModelAndView modelAndView = new ModelAndView("redirect:/task/add");
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, "task adding");
            return modelAndView;
        }
    }

    private String getPath(HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("");
        path = path.replace('\\', '/');

        String fullSavePath;
        if (path.endsWith("/")) {
            fullSavePath = path + ApplicationConstants.SAVE_DIRECTORY;
        } else {
            fullSavePath = path + "/" + ApplicationConstants.SAVE_DIRECTORY;
        }
        return fullSavePath;
    }

    @PostMapping("/update")
    public ModelAndView updateTask(@ModelAttribute Task task, @RequestParam String date, HttpServletRequest request) {
        try {
            log.info("user tries update task");
            request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

            Task taskForUpdate = taskService.getTaskById(task.getId());
            taskForUpdate.setName(task.getName());
            taskForUpdate.setDescription(task.getDescription());
            taskForUpdate.setDateCompletion(dateParser.getLocalDateTime(date));
            taskService.updateTask(taskForUpdate);

            log.info("the user has successfully updated the task");
            ModelAndView modelAndView = new ModelAndView("redirect:/task/edit");
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, ApplicationConstants.DATA_UPDATED_MSG);
            modelAndView.addObject(ApplicationConstants.TASK_ID, task.getId());
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception in updateTask method", e);
            request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
            ModelAndView modelAndView = new ModelAndView("redirect:/task/edit",
                    ApplicationConstants.ERROR_KEY, ERROR_TASK_UPDATE_MESSAGE);
            modelAndView.addObject(ApplicationConstants.TASK_ID, task.getId());
            return modelAndView;
        }
    }

    @PostMapping("/fix")
    public ModelAndView fixTask(@RequestParam long taskId, @RequestParam String section) {
        return updateTaskStatus(taskId, section, task -> {
            task.setCompleted(true);
            return task;
        });
    }

    @PostMapping("/restore")
    public ModelAndView restoreTask(@RequestParam long taskId, @RequestParam String section) {
        return updateTaskStatus(taskId, section, task -> {
            task.setCompleted(false);
            task.setDeleted(false);
            return task;
        });
    }

    @PostMapping("/delete")
    public ModelAndView deleteTask(@RequestParam long taskId, @RequestParam String section) {
        return updateTaskStatus(taskId, section, task -> {
            task.setDeleted(true);
            return task;
        });
    }

    @PostMapping("/fulDelete")
    public ModelAndView fulDeleteTask(@RequestParam long taskId, @RequestParam String section) {
        log.info("user tries delete task");
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks/" + section);
        try {
            Task task = taskService.getTaskById(taskId);
            if (task.getFileInfo() != null) {
                fileService.delete(task.getFileInfo());
            }
            taskService.deleteTask(taskId);
            log.info("the user has successfully deleted the task");
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method fulDeleteTask", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, ERROR_TASK_UPDATE_MESSAGE);
            return modelAndView;
        }
    }

    @GetMapping("/clearAll")
    public ModelAndView clearListDeletedTasks(HttpSession session) {
        log.info("user tries clear list deleted tasks");
        ModelAndView modelAndView = new ModelAndView("redirect:/tasks/deleted");
        try {
            User user = (User) session.getAttribute(ApplicationConstants.USER_KEY);
            taskService.deleteAllUserDeletedTask(user.getId());
            log.info("the user has successfully cleared the list of deleted tasks");
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method clearListDeletedTasks", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, "clear list");
            return modelAndView;
        }
    }

    private ModelAndView updateTaskStatus(@RequestParam long taskId, @RequestParam String section, Function<Task, Task> function) {
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