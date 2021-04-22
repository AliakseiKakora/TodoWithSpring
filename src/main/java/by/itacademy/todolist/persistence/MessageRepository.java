package by.itacademy.todolist.persistence;

import by.itacademy.todolist.model.Message;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {

    @Override
    @EntityGraph(attributePaths = {"user", "user.profile"})
    Optional<Message> findById(Long aLong);

    @Override
    @EntityGraph(attributePaths = {"user", "user.profile"})
    Iterable<Message> findAll();

}
