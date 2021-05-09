package by.itacademy.todolist.persistence;

import by.itacademy.todolist.model.FileInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FileInfoRepository extends CrudRepository<FileInfo, Long> {

    @Override
    @Query("select f from  FileInfo f join fetch f.task")
    Optional<FileInfo> findById(Long aLong);
}