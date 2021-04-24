package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j

@Controller
public class AuthenticateController {

    private static final String ERROR_MESSAGE = "Invalid user data";
    private static final String LOGIN_PAGE = "login";
    private static final String MAIN_PAGE = "main";

    @Autowired
    private UserService userService;


    @GetMapping(value = "/login")
    public ModelAndView loadLoginPage() {
        return new ModelAndView(LOGIN_PAGE);
    }

    @GetMapping(value = "/main")
    public ModelAndView loadMainPage() {
        return new ModelAndView(MAIN_PAGE);
    }


    @PostMapping("/login")
    public ModelAndView login(@RequestParam String login, @RequestParam String password, HttpSession session) {
        try {
            log.info("user tries to login with login credentials: login - {}", login);

            User user = userService.getUserByLoginAndPassword(login, password);
            session.setAttribute(ApplicationConstants.USER_KEY, user);
            return new ModelAndView("redirect:/" + MAIN_PAGE);

        } catch (Exception e) {
            log.warn("exception authenticate user with login - {} ", login, e);
            return new ModelAndView(LOGIN_PAGE, ApplicationConstants.ERROR_KEY, ERROR_MESSAGE);
        }
    }
}