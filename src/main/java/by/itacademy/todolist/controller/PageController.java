package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.security.UserDetailsImpl;
import by.itacademy.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor

@Controller
public class PageController {

    private static final String INDEX_PAGE = "index";
    private static final String REGISTRATION_PAGE = "registration";
    private static final String MAIN_PAGE = "main";
    private static final String PROFILE_PAGE = "profile";
    private static final String LOGIN_PAGE = "login";

    private final TaskService taskService;

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
    public ModelAndView loadErrorPage() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
            long id = principal.getId();
            return new ModelAndView("error", ApplicationConstants.USER_ID_KEY, id);
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

    @GetMapping("task/edit")
    public ModelAndView loadEditTaskPage(@RequestParam Long taskId, @RequestParam(required = false) String successful,
                                          @RequestParam(required = false) String error) {
        try {
            Task task = taskService.getTaskById(taskId);
            ModelAndView modelAndView = new ModelAndView("editTask");
            modelAndView.addObject(ApplicationConstants.TASK_KEY, task);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successful);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception in loadEditTaskPage method ", e);
            return new ModelAndView("redirect:/error");
        }
    }

    @GetMapping("/block")
    public ModelAndView loadBlockedPage(@RequestParam(required = false) String error,
                                        @RequestParam(required = false) String successful) {
        ModelAndView modelAndView = new ModelAndView("blocked");
        modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
        modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successful);
        return modelAndView;
    }

    @GetMapping("/security")
    public ModelAndView loadSecurityPage() {
        return new ModelAndView("security");
    }
}