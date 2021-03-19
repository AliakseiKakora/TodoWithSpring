package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.persistence.dao.FileInfoDao;

public class FileInfoDaoJpa extends AbstractDaoJpa<FileInfo> implements FileInfoDao<FileInfo> {


    //todo need for todo???
    @Override
    public FileInfo addFileInfoForTask(FileInfo fileInfo, long task_id) {
        return null;
    }
}
