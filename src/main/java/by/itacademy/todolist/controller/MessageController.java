package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.MessageService;
import by.itacademy.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.CurrentSecurityContext;
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

    @PostMapping("/messages/create")
    public ModelAndView createMessage(@RequestParam String message,
                                      @CurrentSecurityContext(expression = "authentication.principal.id") Long userId) {
        User user = userService.getById(userId);
        return createAndSaveMessage(message, user, new ModelAndView("redirect:/main"));
    }

    @PostMapping("/messages")
    public ModelAndView createMessage(@RequestParam String message,
                                      @RequestParam String userLogin) {
        User user = userService.getByLogin(userLogin);
        return createAndSaveMessage(message, user, new ModelAndView("redirect:/login"));
    }

    private ModelAndView createAndSaveMessage(String message, User user, ModelAndView modelAndView) {
        log.info("the user is trying to send a message ");
        try {
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