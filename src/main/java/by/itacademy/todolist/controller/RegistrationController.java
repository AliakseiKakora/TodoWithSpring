package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor

@Controller
public class RegistrationController {

    private final ProfileService profileService;
    private final UserService userService;
    private final UserDetailsParser userDetailsParser;

    @PostMapping(value = "/registration")
    public ModelAndView registration(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        try {
            log.info("user tries to sign up with credentials: email - {}; login - {}", user.getEmail(), user.getProfile().getLogin());
            if (bindingResult.hasErrors()) {
                return  new ModelAndView(ApplicationConstants.REGISTRATION_PAGE);
            }

            if (profileService.existLoginOrEmail(user.getProfile().getLogin(), user.getEmail())) {
                return  new ModelAndView("redirect:/registration",
                        ApplicationConstants.ERROR_KEY, "Login or email address exists");
            }
            user.addProfile(user.getProfile());
            user = userService.save(user);

            UserDetailsImpl userDetails = userDetailsParser.parseUserToUserDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("user was able to register with credentials: email - {}; login - {}", user.getEmail(), user.getProfile().getLogin());
            return new ModelAndView("redirect:/" + ApplicationConstants.MAIN_PAGE);
        } catch (Exception e) {
            log.warn("exception registration user with credentials - {} {} ", user.getEmail(), user.getProfile().getLogin(), e);
            return new ModelAndView("redirect:/" + ApplicationConstants.REGISTRATION_PAGE, ApplicationConstants.ERROR_KEY, "registration");
        }
    }
}