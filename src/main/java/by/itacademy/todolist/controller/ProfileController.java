package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String PROFILE_PAGE = "profile";

    private final UserService userService;

    @PostMapping("/update")
    public ModelAndView updateProfile(@RequestParam String login, @RequestParam String password,
                                      @RequestParam String email, @RequestParam String name,
                                      @RequestParam String surname, HttpSession session) {
        User user = (User) session.getAttribute(ApplicationConstants.USER_KEY);
        try {
            user.getProfile().setLogin(login);
            user.getProfile().setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);

            userService.update(user);
            log.info("user {} has updated his data", user);

            return new ModelAndView("redirect:/" + PROFILE_PAGE,
                    ApplicationConstants.SUCCESSFUL_KEY, ApplicationConstants.DATA_UPDATED_MSG);

        } catch (Exception e) {
            log.warn("exception update user profile for - {} ", user, e);
            return new ModelAndView("redirect:/" + PROFILE_PAGE,
                    ApplicationConstants.ERROR_KEY, ApplicationConstants.DATA_UPDATED_MSG);
        }
    }
}