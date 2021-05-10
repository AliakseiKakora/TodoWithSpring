package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.security.UserDetailsImpl;
import by.itacademy.todolist.security.UserDetailsParser;
import by.itacademy.todolist.service.ProfileService;
import by.itacademy.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequiredArgsConstructor

@Controller
public class RegistrationController {

    private final ProfileService profileService;
    private final UserService userService;
    private final UserDetailsParser userDetailsParser;

    @PostMapping(value = "/registration")
    public ModelAndView registration(Profile profile, User user) {
        try {
            log.info("user tries to sign up with credentials: email - {}; login - {}", user.getEmail(), profile.getLogin());
            if (profileService.existLoginOrEmail(profile.getLogin(), user.getEmail())) {
                throw new RuntimeException("Login or email is busy");
            }
            user.addProfile(profile);
            user = userService.save(user);

            UserDetailsImpl userDetails = userDetailsParser.parseUserToUserDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("user was able to register with credentials: email - {}; login - {}", user.getEmail(), profile.getLogin());
            return new ModelAndView("redirect:/" + ApplicationConstants.MAIN_PAGE);
        } catch (Exception e) {
            log.warn("exception registration user with credentials - {} {} ", user.getEmail(), profile.getLogin(), e);
            return new ModelAndView("redirect:/" + ApplicationConstants.REGISTRATION_PAGE, ApplicationConstants.ERROR_KEY, e.getMessage());
        }
    }
}