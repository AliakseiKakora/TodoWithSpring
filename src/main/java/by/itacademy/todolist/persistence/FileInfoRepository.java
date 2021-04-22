package by.itacademy.todolist.persistence;

import by.itacademy.todolist.model.FileInfo;
import org.springframework.data.repository.CrudRepository;

public interface FileInfoRepository extends CrudRepository<FileInfo, Long> {

}
