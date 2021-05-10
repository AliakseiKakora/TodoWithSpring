package by.itacademy.todolist.persistence;

import by.itacademy.todolist.model.FileInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FileInfoRepository extends CrudRepository<FileInfo, Long> {

    @Override
    @Query("select f from  FileInfo f join fetch f.task where f.id = :aLong")
    Optional<FileInfo> findById(@Param("aLong") Long aLong);
}