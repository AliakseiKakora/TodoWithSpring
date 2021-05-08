package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RequiredArgsConstructor

@Controller
@RequestMapping("/tasks")
public class LoadTasksController {

    private final TaskService taskService;

    @GetMapping("/{section}")
    public ModelAndView loadTasks(@PathVariable String section, @RequestParam(required = false) String error,
                                  @SessionAttribute User user) {
        try {
            ModelAndView modelAndView = new ModelAndView("tasks");
            List<Task> tasks = taskService.getUserTasksBySection(user.getId(), section);

            modelAndView.addObject(ApplicationConstants.TASKS_KEY, tasks);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception in loadTasks method ", e);
            return new ModelAndView("redirect:/error");
        }
    }


//    @ExceptionHandler({Exception.class})
//    private ModelAndView handleException(Exception e) {
//        log.warn("exception in loadTasks method ", e);
//        return new ModelAndView("redirect:/error");
//    }
}