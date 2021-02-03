package by.itacademy.todolist.persistance.dao;

public interface FileInfoDao<FileInfo> extends CrudDao<FileInfo> {

    FileInfo addFileInfoForTask(FileInfo fileInfo, long task_id);

}