package by.itacademy.todolist.persistence;

import by.itacademy.todolist.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Override
    @EntityGraph(attributePaths = {"roles", "profile"})
    Optional<User> findById(Long aLong);

    @Override
    @EntityGraph(attributePaths = {"roles", "profile"})
    Iterable<User> findAll();

    @EntityGraph(attributePaths = {"roles", "profile"})
    Optional<User> findByProfileLoginAndProfilePassword(@Param("login") String login, @Param("password") String password);

}