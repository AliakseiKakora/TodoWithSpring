package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.TaskService;
import by.itacademy.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j

@Controller
public class PageController {

    private static final String INDEX_PAGE = "index";
    private static final String REGISTRATION_PAGE = "registration";
    private static final String MAIN_PAGE = "main";
    private static final String PROFILE_PAGE = "profile";
    private static final String LOGIN_PAGE = "login";

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ModelAndView loadWelcomePage() {
        return new ModelAndView(INDEX_PAGE);
    }

    @GetMapping(value = "/login")
    public ModelAndView loadLoginPage() {
        return new ModelAndView(LOGIN_PAGE);
    }

    @GetMapping(value = "/main")
    public ModelAndView loadMainPage() {
        return new ModelAndView(MAIN_PAGE);
    }

    @GetMapping("/error")
    public ModelAndView loadErrorPage(HttpSession session) {
        try {
            User user = (User) session.getAttribute(ApplicationConstants.USER_KEY);
            long userId = user.getId();
            return new ModelAndView("error", ApplicationConstants.USER_ID_KEY, userId );

        } catch (Exception e) {
            log.warn("exception load error page ", e);
            return new ModelAndView("main");
        }
    }

    @GetMapping(value = "/registration")
    public ModelAndView loadRegistrationPage(HttpServletRequest request) {
        String errorKey = ApplicationConstants.ERROR_KEY;
        return new ModelAndView(REGISTRATION_PAGE, errorKey, request.getParameter(errorKey));
    }

    @GetMapping("/task/add")
    public ModelAndView loadAddTaskPage(HttpServletRequest request) {
        try {
            String section = request.getParameter(ApplicationConstants.SECTION_KEY);
            String successfulMessage = request.getParameter(ApplicationConstants.SUCCESSFUL_KEY);
            String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);

            ModelAndView modelAndView = new ModelAndView("addTask");
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successfulMessage);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, errorMessage);

            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in loadTaskPage method ", e);
            return new ModelAndView("redirect:/error");
        }
    }

    @PostMapping("task/edit")
    public ModelAndView loadEditTaskPage(@RequestParam long taskId, HttpServletRequest request) {
        try {
            String successfulMessage = request.getParameter(ApplicationConstants.SUCCESSFUL_KEY);
            String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);

            Task task = taskService.getTaskById(taskId);
            ModelAndView modelAndView = new ModelAndView("editTask");
            modelAndView.addObject(ApplicationConstants.TASK_KEY, task);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successfulMessage);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, errorMessage);
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception in loadEditTaskPage method ", e);
            return new ModelAndView("redirect:/error");
        }
    }

    @GetMapping("/profile")
    public ModelAndView loadProfilePage(HttpServletRequest request) {
        try {
            ModelAndView modelAndView = new ModelAndView(PROFILE_PAGE);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, request.getParameter(ApplicationConstants.ERROR_KEY));
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, request.getParameter(ApplicationConstants.SUCCESSFUL_KEY));

            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            user = userService.getById(user.getId());
            request.getSession().setAttribute(ApplicationConstants.USER_KEY, user);
            modelAndView.addObject(ApplicationConstants.USER_KEY, user);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception load profile page", e);
            return new ModelAndView("redirect:/" + MAIN_PAGE);
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return new ModelAndView("redirect:/login");
    }
}