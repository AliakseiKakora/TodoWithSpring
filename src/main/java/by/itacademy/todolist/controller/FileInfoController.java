package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.service.FileService;
import by.itacademy.todolist.service.SecurityService;
import by.itacademy.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;

@RequiredArgsConstructor
@Slf4j

@Controller
@RequestMapping("/file")
public class FileInfoController {

    private static final String SUCCESSFUL_DELETE_MESSAGE = "file was deleted";
    private static final String ERROR_DELETE_MESSAGE = "file delete error";
    private static final String FILE_UPLOAD_MESSAGE = "file upload";

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

    @PostMapping("/upload")
    public ModelAndView uploadFile(@RequestParam MultipartFile file, @RequestParam Long taskId,
                                   HttpServletRequest request) {
        log.info("user tries upload file");
        ModelAndView modelAndView = new ModelAndView("redirect:/task/edit", ApplicationConstants.TASK_ID, taskId);
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            fileService.addFileInfoForTask(file, taskId, user.getId(), request);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, FILE_UPLOAD_MESSAGE);
            log.info("user successfully upload file");
            return modelAndView;
        } catch (Exception e) {
            log.warn("exception in method uploadFile", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, FILE_UPLOAD_MESSAGE);
            return modelAndView;
        }
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam Long taskId, @RequestParam Long fileId,
                                     HttpServletResponse response, HttpServletRequest request) {
        log.info("user tries download file");
        try {
            FileInfo fileInfo = fileService.getById(fileId);
            Task task = taskService.getTaskById(taskId);
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            securityService.checkRightToTask(task, user);

            File file = new File(fileInfo.getPath());
            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = Files.readAllBytes(file.toPath());
            inputStream.close();

            String contentType = request.getServletContext().getMimeType(fileInfo.getName());
            response.setHeader("Content-Type", contentType);
            response.setHeader("Content-Length", String.valueOf(bytes.length));
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileInfo.getName() + "\"");
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            log.warn("exception in method downloadFile", e);
        }
    }

}