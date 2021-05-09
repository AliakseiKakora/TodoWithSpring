package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.security.SecurityContextManager;
import by.itacademy.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private static final String PROFILE_PAGE = "profile";

    private final UserService userService;
    private final SecurityContextManager securityContextManager;

    @GetMapping
    public ModelAndView loadProfilePage(@RequestParam(required = false) String successful,
                                        @RequestParam(required = false) String error) {
        try {
            ModelAndView modelAndView = new ModelAndView(PROFILE_PAGE);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successful);
            long userId = securityContextManager.getUserId();
            User user = userService.getById(userId);
            modelAndView.addObject(ApplicationConstants.USER_KEY, user);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception load profile page", e);
            return new ModelAndView("redirect:/main");
        }
    }

    @PostMapping("/update")
    public ModelAndView updateProfile(@ModelAttribute User userForm) {
        try {
            long userId = securityContextManager.getUserId();
            User user = userService.getById(userId);
            user.setName(userForm.getName());
            user.setSurname(userForm.getSurname());
            user.setEmail(userForm.getEmail());
            user.getProfile().setLogin(userForm.getProfile().getLogin());
            user.getProfile().setPassword(userForm.getProfile().getPassword());

            userService.update(user);
            log.info("user {} has updated his data", userForm);
            return new ModelAndView("redirect:/" + PROFILE_PAGE,
                    ApplicationConstants.SUCCESSFUL_KEY, ApplicationConstants.DATA_UPDATED_MSG);

        } catch (Exception e) {
            log.warn("exception update user profile", e);
            return new ModelAndView("redirect:/" + PROFILE_PAGE,
                    ApplicationConstants.ERROR_KEY, ApplicationConstants.DATA_UPDATED_MSG);
        }
    }
}