package by.itacademy.todolist.persistence.dao;

public interface FileInfoDao<FileInfo> extends CrudDao<FileInfo> {

    FileInfo addFileInfoForTask(FileInfo fileInfo, long task_id);

}