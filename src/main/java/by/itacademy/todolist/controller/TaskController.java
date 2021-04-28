package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.FileService;
import by.itacademy.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor


@Controller
public class TaskController {

    private final TaskService taskService;
    private final FileService fileService;

    @PostMapping("/task/add")
    public ModelAndView addTask(HttpServletRequest request, @RequestParam MultipartFile file,
                                @RequestParam String section, @RequestParam String name,
                                @RequestParam String description) {
        log.info("user tries to add a task");
        try {
            String date = request.getParameter("date");
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            Task task = Task.builder().name(name).description(description).dateAdded(LocalDateTime.now()).build();
            task.setUser(user);

            task = taskService.saveTaskToSection(task, section, date);
            fileService.addFileInfoForTask(file, task.getId(), user.getId(), getPath(request));

            ModelAndView modelAndView = new ModelAndView("redirect:/task/add");
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, "task added");

            log.info("user added task {}", task);
            return modelAndView;

        } catch (Exception e) {
            log.warn("exception in addTask method ", e);
            ModelAndView modelAndView = new ModelAndView("redirect:/task/add");
            modelAndView.addObject(ApplicationConstants.SECTION_KEY, section);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, "task adding");
            return modelAndView;
        }
    }

    private String getPath(HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("");
        path = path.replace('\\', '/');

        String fullSavePath;
        if (path.endsWith("/")) {
            fullSavePath = path + ApplicationConstants.SAVE_DIRECTORY;
        } else {
            fullSavePath = path + "/" + ApplicationConstants.SAVE_DIRECTORY;
        }
        return fullSavePath;
    }
}