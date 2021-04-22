package by.itacademy.todolist.persistence;

import by.itacademy.todolist.model.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    @EntityGraph(attributePaths = "fileInfo")
    List<Task> findByUserId(Long userId);

    @Override
    @EntityGraph(attributePaths = "fileInfo")
    Optional<Task> findById(Long aLong);

    @Modifying
    @Transactional
    @Query("delete from Task t where t.user.id = :userId")
    void deleteTasksByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("delete from Task t where t.isDeleted = true and t.user.id =:userId")
    void deleteUserTasksMarkedAsDeleted(@Param("userId") Long userId);

}