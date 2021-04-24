package by.itacademy.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    private static final String INDEX_PAGE = "index";

    @GetMapping("/")
    public ModelAndView loadWelcomePage() {
        return new ModelAndView(INDEX_PAGE);
    }
}