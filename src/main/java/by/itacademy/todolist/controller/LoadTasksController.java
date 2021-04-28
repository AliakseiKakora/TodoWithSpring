package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j

@Controller
@RequestMapping("/tasks")
public class LoadTasksController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/today")
    public ModelAndView loadTodayTasks(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            ModelAndView modelAndView = new ModelAndView("tasks");
            List<Task> todayTasks = taskService.getTodayUserTasks(user.getId());

            modelAndView.addObject(ApplicationConstants.TASKS_KEY, todayTasks);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, ApplicationConstants.SECTION_TODAY);
            modelAndView.addObject(ApplicationConstants.TASK_TITLE, ApplicationConstants.TASK_TODAY_TITLE);
            return modelAndView;

        } catch (Exception e) {
            return handleException(e);
        }
    }


    @GetMapping("/tomorrow")
    public ModelAndView loadTomorrowTasks(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            ModelAndView modelAndView = new ModelAndView("tasks");
            List<Task> tomorrowTasks = taskService.getTomorrowUserTasks(user.getId());

            modelAndView.addObject(ApplicationConstants.TASKS_KEY, tomorrowTasks);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, ApplicationConstants.SECTION_TOMORROW);
            modelAndView.addObject(ApplicationConstants.TASK_TITLE, ApplicationConstants.TASK_TOMORROW_TITLE);
            return modelAndView;

        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/someDay")
    public ModelAndView loadSomeDayTasks(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            ModelAndView modelAndView = new ModelAndView("tasks");
            List<Task> someDayTasks = taskService.getSomeDayUserTasks(user.getId());

            modelAndView.addObject(ApplicationConstants.TASKS_KEY, someDayTasks);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, ApplicationConstants.SECTION_SOME_DAY);
            modelAndView.addObject(ApplicationConstants.TASK_TITLE, ApplicationConstants.TASK_SOME_DAY_TITLE);
            return modelAndView;

        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/fixed")
    public ModelAndView loadFixedTasks(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            ModelAndView modelAndView = new ModelAndView("tasks");
            List<Task> fixedTasks = taskService.getFixedUserTasks(user.getId());

            modelAndView.addObject(ApplicationConstants.TASKS_KEY, fixedTasks);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, ApplicationConstants.SECTION_FIXED);
            modelAndView.addObject(ApplicationConstants.TASK_TITLE, ApplicationConstants.TASK_FIXED_TITLE);
            return modelAndView;

        } catch (Exception e) {
            return handleException(e);
        }
    }

    @GetMapping("/deleted")
    public ModelAndView loadDeletedTasks(HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            ModelAndView modelAndView = new ModelAndView("tasks");
            List<Task> deletedTasks = taskService.getDeletedUserTasks(user.getId());

            modelAndView.addObject(ApplicationConstants.TASKS_KEY, deletedTasks);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, ApplicationConstants.SECTION_DELETED);
            modelAndView.addObject(ApplicationConstants.TASK_TITLE, ApplicationConstants.TASK_DELETED_TITLE);
            return modelAndView;

        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ModelAndView handleException(Exception e) {
        log.warn("exception in loadTasks method ", e);
        return new ModelAndView("redirect:/error");
    }
}