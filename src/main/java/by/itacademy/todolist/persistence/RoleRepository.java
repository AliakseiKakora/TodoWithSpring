package by.itacademy.todolist.persistence;

import by.itacademy.todolist.model.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Override
    @EntityGraph(attributePaths = {"users", "users.profile"})
    Optional<Role> findById(Long id);

    @EntityGraph(attributePaths = {"users", "users.profile"})
    Role findByRole(String role);

    @Modifying
    @Transactional
    @Query(value = "delete from role_user where user_id =:userId", nativeQuery = true)
    void deleteAllUserRoles(@Param("userId") Long userId);

}