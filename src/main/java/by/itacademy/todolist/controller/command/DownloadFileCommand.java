package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class DownloadFileCommand extends FrontCommand {

    private static final String ERROR_VIEW = "/?command=ErrorView";

    @Override
    public void process() throws ServletException, IOException {
        try {
            String fileName = request.getParameter(ApplicationConstants.FILE_NAME);
            String path = request.getParameter(ApplicationConstants.FILE_PATH);
            File file = new File(path);
            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = Files.readAllBytes(file.toPath());
            inputStream.close();

            String contentType = context.getMimeType(fileName);
            response.setHeader("Content-Type", contentType);
            response.setHeader("Content-Length", String.valueOf(bytes.length));
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");

            response.getOutputStream().write(bytes);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + ERROR_VIEW);
    }
}