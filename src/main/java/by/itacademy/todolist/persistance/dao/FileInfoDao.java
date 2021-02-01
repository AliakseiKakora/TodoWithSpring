package by.itacademy.todolist.persistance.dao;

public interface FileInfoDao<FileInfo> {

    FileInfo addFileInfoForTask(FileInfo fileInfo, long task_id);

}
