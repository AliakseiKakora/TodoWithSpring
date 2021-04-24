package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String PROFILE_PAGE = "profile";
    private static final String MAIN_PAGE = "main";

    @Autowired
    private UserService userService;

    @GetMapping
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