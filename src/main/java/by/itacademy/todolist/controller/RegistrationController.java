package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.ProfileService;
import by.itacademy.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j

@Controller
public class RegistrationController {

    private static final String REGISTRATION_PAGE = "registration";
    private static final String MAIN_PAGE = "main";


    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/registration")
    public ModelAndView loadRegistrationPage(HttpServletRequest request) {
        String errorKey = ApplicationConstants.ERROR_KEY;
        return new ModelAndView(REGISTRATION_PAGE, errorKey, request.getParameter(errorKey));
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration(Profile profile, User user, HttpSession session) {
        try {
            log.info("user tries to sign up with credentials: email - {}; login - {}", user.getEmail(), profile.getLogin());
            if (profileService.existLoginOrEmail(profile.getLogin(), user.getEmail())) {
                throw new RuntimeException("Login or email is busy");
            }
            user.addProfile(profile);
            user = userService.save(user);

            session.setAttribute(ApplicationConstants.USER_KEY, user);
            log.info("user was able to register with credentials: email - {}; login - {}", user.getEmail(), profile.getLogin());

            return new ModelAndView("redirect:/" + MAIN_PAGE);
        } catch (Exception e) {
            log.warn("exception registration user with credentials - {} {} ", user.getEmail(), profile.getLogin(), e);
            return new ModelAndView("redirect:/" + REGISTRATION_PAGE, ApplicationConstants.ERROR_KEY, e.getMessage());
        }
    }
}