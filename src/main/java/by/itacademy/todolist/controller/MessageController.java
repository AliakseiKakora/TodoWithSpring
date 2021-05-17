package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.security.SecurityContextManager;
import by.itacademy.todolist.service.MessageService;
import by.itacademy.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor

@Controller
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final SecurityContextManager securityContextManager;

    @PostMapping("/messages")
    public ModelAndView createMessage(@RequestParam String message,
                                      @RequestParam(required = false) String userLogin) {
        log.info("the user is trying to send a message ");
        ModelAndView modelAndView = new ModelAndView("redirect:/main");
        if (userLogin != null && !userLogin.equals("")) {
            modelAndView.setViewName("redirect:/login");
        }
        try {
            User user;
            if (userLogin != null && !userLogin.equals("")) {
                user = userService.getByLogin(userLogin);
            } else {
                long userId = securityContextManager.getUserId();
                user = userService.getById(userId);
            }
            Message mes = Message.builder()
                    .message(message)
                    .dateAdded(LocalDateTime.now())
                    .user(user)
                    .build();
            messageService.save(mes);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, "submit message");
            log.info("user sent message successfully");
        } catch (Exception e) {
            log.warn("exception in createMessage method", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, "submit message");
        }
        return modelAndView;
    }
}