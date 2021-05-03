package by.itacademy.todolist.service;

import by.itacademy.todolist.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileService {

    FileInfo addFileInfoForTask(MultipartFile file, long taskId, long userId, HttpServletRequest request);

    void delete(FileInfo fileInfo);

    FileInfo getById(long fileId);

}
