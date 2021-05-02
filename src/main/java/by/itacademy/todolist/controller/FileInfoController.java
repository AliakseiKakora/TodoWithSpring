package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.FileService;
import by.itacademy.todolist.service.TaskService;
import by.itacademy.todolist.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Slf4j

@Controller
@RequestMapping("/file")
public class FileInfoController {

    private static final String SUCCESSFUL_DELETE_MESSAGE = "file was deleted";
    private static final String ERROR_DELETE_MESSAGE = "file delete error";

    private final FileService fileService;
    private final TaskService taskService;
    private final SecurityService securityService;

    @GetMapping("/delete")
    public ModelAndView deleteFileInfo(@RequestParam Long taskId, @RequestParam Long fileId, HttpSession session) {
        log.info("user tries delete file");
        ModelAndView modelAndView = new ModelAndView("redirect:/task/edit", ApplicationConstants.TASK_ID, taskId);
        try {
            User user = (User) session.getAttribute(ApplicationConstants.USER_KEY);
            FileInfo fileInfo = fileService.getById(fileId);
            Task task = taskService.getTaskById(taskId);
            securityService.checkRightToTask(task, user);
            fileService.delete(fileInfo);

            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, SUCCESSFUL_DELETE_MESSAGE);
            log.info("user successfully deleted file");
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception im deleteFileInfo method", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, ERROR_DELETE_MESSAGE);
            return modelAndView;
        }
    }

}
