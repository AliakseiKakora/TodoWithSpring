package by.itacademy.todolist.service;

import by.itacademy.todolist.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileInfo addFileInfoForTask(MultipartFile part, long taskId, long userId, String path);

    void delete(FileInfo fileInfo);

    FileInfo getById(long fileId);

}
