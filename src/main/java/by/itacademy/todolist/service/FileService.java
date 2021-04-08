package by.itacademy.todolist.service;

import by.itacademy.todolist.model.FileInfo;

import javax.servlet.http.Part;

public interface FileService {

    FileInfo addFileInfoForTask(Part part, long taskId, long userId, String path);

    void delete(FileInfo fileInfo);

    FileInfo getById(long fileId);

}
