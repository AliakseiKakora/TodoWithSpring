package by.itacademy.todolist.persistence;

import by.itacademy.todolist.model.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    boolean existsByLoginOrUserEmailIgnoreCase(String login, String email);

    @Modifying
    @Transactional
    @Query("update Profile set password=:password where login=:login")
    void updatePasswordByLogin(@Param("password") String password , @Param("login") String login);
}