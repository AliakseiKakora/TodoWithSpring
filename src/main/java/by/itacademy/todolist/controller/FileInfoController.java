package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/delete")
    public ModelAndView deleteFileInfo(@RequestParam Long taskId, @RequestParam Long fileId) {
        log.info("user tries delete file");
        ModelAndView modelAndView = new ModelAndView("redirect:/task/edit", ApplicationConstants.TASK_ID, taskId);
        try {
            FileInfo fileInfo = fileService.getById(fileId);
            fileService.delete(fileInfo);

            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, SUCCESSFUL_DELETE_MESSAGE);
            log.info("user successfully deleted file");
        } catch (Exception e) {
            log.warn("exception im deleteFileInfo method", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, ERROR_DELETE_MESSAGE);
        }
        return modelAndView;
    }

    @PostMapping("/upload")
    public ModelAndView uploadFile(@CurrentSecurityContext(expression = "authentication.principal.id") Long userId,
                                   @RequestParam MultipartFile file,
                                   @RequestParam Long taskId) {
        log.info("user tries upload file");
        ModelAndView modelAndView = new ModelAndView("redirect:/task/edit", ApplicationConstants.TASK_ID, taskId);
        try {
            fileService.addFileInfoForTask(file, taskId, userId);
            modelAndView.addObject(ApplicationConstants.SUCCESSFUL_KEY, FILE_UPLOAD_MESSAGE);
            log.info("user successfully upload file");
        } catch (Exception e) {
            log.warn("exception in method uploadFile", e);
            modelAndView.addObject(ApplicationConstants.ERROR_KEY, FILE_UPLOAD_MESSAGE);
        }
        return modelAndView;
    }

    @GetMapping("/download")
    public void downloadFile(@RequestParam Long fileId,
                             HttpServletResponse response,
                             HttpServletRequest request) {
        log.info("user tries download file");
        try {
            FileInfo fileInfo = fileService.getById(fileId);

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