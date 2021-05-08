package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor

@Controller
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ModelAndView createMessage(@SessionAttribute User user, @RequestParam String message) {
        try {
            Message mes = Message.builder()
                    .message(message)
                    .dateAdded(LocalDateTime.now())
                    .user(user)
                    .build();
            messageService.save(mes);
            ModelAndView modelAndView = new ModelAndView("redirect:/main");
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, "submit message");
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/error",
                    ApplicationConstants.ERROR_KEY, "submit message");
        }
    }
}
