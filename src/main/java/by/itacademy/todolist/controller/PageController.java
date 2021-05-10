package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor

@Controller
public class PageController {

    private final TaskService taskService;

    @GetMapping("/")
    public ModelAndView loadWelcomePage() {
        return new ModelAndView(ApplicationConstants.INDEX_PAGE);
    }

    @GetMapping(value = "/login")
    public ModelAndView loadLoginPage(@RequestParam(required = false) String error) {
        return new ModelAndView(ApplicationConstants.LOGIN_PAGE, ApplicationConstants.ERROR_KEY, error);
    }

    @GetMapping(value = "/main")
    public ModelAndView loadMainPage(@RequestParam(required = false) String successful) {
        ModelAndView modelAndView = new ModelAndView(ApplicationConstants.MAIN_PAGE);
        modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successful);
        return modelAndView;
    }

    @GetMapping("/error")
    public ModelAndView loadErrorPage(@RequestParam(required = false) String error) {
        try {
            ModelAndView modelAndView = new ModelAndView(ApplicationConstants.ERROR_PAGE);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception load error page ", e);
            return new ModelAndView(ApplicationConstants.MAIN_PAGE);
        }
    }

    @GetMapping(value = "/registration")
    public ModelAndView loadRegistrationPage(@RequestParam(required = false) String error) {
        return new ModelAndView(ApplicationConstants.REGISTRATION_PAGE, ApplicationConstants.ERROR_KEY, error);
    }

    @GetMapping("/task/add")
    public ModelAndView loadAddTaskPage(@RequestParam String section,
                                        @RequestParam(required = false) String error,
                                        @RequestParam(required = false) String successful) {
        try {
            ModelAndView modelAndView = new ModelAndView(ApplicationConstants.ADD_TASK_PAGE);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successful);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);

            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in loadTaskPage method ", e);
            return new ModelAndView("redirect:/" + ApplicationConstants.ERROR_PAGE);
        }
    }

    @GetMapping("/task/edit")
    public ModelAndView loadEditTaskPage(@RequestParam Long taskId,
                                         @RequestParam(required = false) String successful,
                                         @RequestParam(required = false) String error) {
        try {
            Task task = taskService.getTaskById(taskId);
            ModelAndView modelAndView = new ModelAndView(ApplicationConstants.EDIT_TASK_PAGE);
            modelAndView.addObject(ApplicationConstants.TASK_KEY, task);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successful);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception in loadEditTaskPage method ", e);
            return new ModelAndView("redirect:/" + ApplicationConstants.ERROR_PAGE);
        }
    }

    @GetMapping("/block")
    public ModelAndView loadBlockedPage(@RequestParam(required = false) String error,
                                        @RequestParam(required = false) String successful) {
        ModelAndView modelAndView = new ModelAndView(ApplicationConstants.BLOCKED_PAGE);
        modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
        modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successful);
        return modelAndView;
    }

    @GetMapping("/security")
    public ModelAndView loadSecurityPage() {
        return new ModelAndView(ApplicationConstants.SECURITY_PAGE);
    }
}