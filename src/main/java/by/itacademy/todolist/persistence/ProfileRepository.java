package by.itacademy.todolist.persistence;

import by.itacademy.todolist.model.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    boolean existsByLoginOrUserEmailIgnoreCase(@Param("login") String login, @Param("email") String email);

}