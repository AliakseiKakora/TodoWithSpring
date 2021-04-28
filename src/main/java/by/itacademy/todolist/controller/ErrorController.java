package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Slf4j

@Controller
public class ErrorController {

    @GetMapping("/error")
    public ModelAndView loadErrorPage(HttpSession session) {
       try {
           User user = (User) session.getAttribute(ApplicationConstants.USER_KEY);
           long userId = user.getId();
           return new ModelAndView("error", ApplicationConstants.USER_ID_KEY, userId );

       } catch (Exception e) {
           log.warn("exception load error page ", e);
           return new ModelAndView("main");
       }
    }
}