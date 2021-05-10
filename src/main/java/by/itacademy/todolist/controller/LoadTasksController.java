package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.security.SecurityContextManager;
import by.itacademy.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RequiredArgsConstructor

@Controller
@RequestMapping("/tasks")
public class LoadTasksController {

    private final TaskService taskService;
    private final SecurityContextManager securityContextManager;

    @GetMapping("/{section}")
    public ModelAndView loadTasks(@PathVariable String section, @RequestParam(required = false) String error) {
        try {
            long userId = securityContextManager.getUserId();

            ModelAndView modelAndView = new ModelAndView(ApplicationConstants.TASKS_PAGE);
            List<Task> tasks = taskService.getUserTasksBySection(userId, section);

            modelAndView.addObject(ApplicationConstants.TASKS_KEY, tasks);
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, error);
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception in loadTasks method ", e);
            return new ModelAndView("redirect:/" + ApplicationConstants.ERROR_PAGE);
        }
    }


//    @ExceptionHandler({Exception.class})
//    private ModelAndView handleException(Exception e) {
//        log.warn("exception in loadTasks method ", e);
//        return new ModelAndView("redirect:/error");
//    }
}