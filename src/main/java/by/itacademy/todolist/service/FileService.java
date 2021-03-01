package by.itacademy.todolist.service;

import by.itacademy.todolist.model.FileInfo;

import javax.servlet.http.Part;

public interface FileService {

    FileInfo addFileInfoForTask(Part part, long task_id, long userId, String path);

    void delete(long fileId);

    FileInfo getById(long fileId);

}
