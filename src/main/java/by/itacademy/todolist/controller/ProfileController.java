package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.security.SecurityContextManager;
import by.itacademy.todolist.service.ProfileService;
import by.itacademy.todolist.service.UserService;
import by.itacademy.todolist.validation.UserPasswordForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final ProfileService profileService;
    private final SecurityContextManager securityContextManager;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView loadProfilePage(@RequestParam(required = false) String successful,
                                        @RequestParam(required = false) String error) {
        try {
            ModelAndView modelAndView = new ModelAndView(ApplicationConstants.PROFILE_PAGE);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successful);
            long userId = securityContextManager.getUserId();
            User user = userService.getById(userId);
            modelAndView.addObject(ApplicationConstants.USER_KEY, user);
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception load profile page", e);
            return new ModelAndView("redirect:/" + ApplicationConstants.MAIN_PAGE);
        }
    }

    @PostMapping("/update")
    public ModelAndView updateProfile(@ModelAttribute User userForm) {
        ModelAndView modelAndView = new ModelAndView("redirect:/" + ApplicationConstants.PROFILE_PAGE);
        try {
            long userId = securityContextManager.getUserId();
            User user = userService.getById(userId);
            user.setName(userForm.getName());
            user.setSurname(userForm.getSurname());
            user.setEmail(userForm.getEmail());
            user.getProfile().setLogin(userForm.getProfile().getLogin());

            userService.update(user);
            log.info("user {} has updated his data", userForm);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, ApplicationConstants.DATA_UPDATED_MSG);
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception update user profile", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, ApplicationConstants.DATA_UPDATED_MSG);
            return modelAndView;
        }
    }

    @GetMapping("update/password")
    public ModelAndView loadChangePasswordPage(@RequestParam(required = false) String error,
                                               @RequestParam(required = false) String successful) {
        ModelAndView modelAndView = new ModelAndView(ApplicationConstants.CHANGE_PASSWORD_PAGE);
        modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
        modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, successful);
        modelAndView.addObject(ApplicationConstants.USER_KEY, new UserPasswordForm());
        return modelAndView;
    }

    @PostMapping("/update/password")
    public ModelAndView changePassword(@Valid @ModelAttribute("user") UserPasswordForm newPassword,
                                       BindingResult bindingResult,
                                       @RequestParam String currentPassword,
                                       @RequestParam String confirmPassword) {
        ModelAndView modelAndView = new ModelAndView("redirect:/profile/update/password");
        try {
            if (bindingResult.hasErrors()) {
                return new ModelAndView(ApplicationConstants.CHANGE_PASSWORD_PAGE);
            }

            long userId = securityContextManager.getUserId();
            User user = userService.getById(userId);
            boolean matches = passwordEncoder.matches(currentPassword, user.getProfile().getPassword());

            if (!matches || !newPassword.getPassword().equals(confirmPassword)) {
                modelAndView.addObject(ApplicationConstants.ERROR_KEY, "Invalid password");
                return modelAndView;
            }
            profileService.updatePasswordByLogin(newPassword.getPassword(), user.getProfile().getLogin());
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, "password has been changed");
            return modelAndView;
        } catch (Exception e) {
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, "update password");
            return modelAndView;
        }
    }
}