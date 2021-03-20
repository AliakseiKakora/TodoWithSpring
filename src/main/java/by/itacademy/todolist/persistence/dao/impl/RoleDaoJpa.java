package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistence.dao.RoleDao;
import by.itacademy.todolist.persistence.exception.DaoException;
import org.hibernate.query.internal.QueryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class RoleDaoJpa extends AbstractDaoJpa<Role> implements RoleDao<Role> {

    private static final String GET_WITH_USERS = "select distinct r from Role r " +
            "left join fetch r.users u " +
            "left join fetch u.profile";

    private static final String GET_BY_ID_WITH_USERS = GET_WITH_USERS + " where r.id = :id";

    private static final String GET_BY_NAME_WITH_USERS = GET_WITH_USERS + " where r.role = :role";


    @Override
    public Role getByIdWithUsers(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(GET_BY_ID_WITH_USERS, Role.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + Role.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Role getByNameWithUsers(String role) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(GET_BY_NAME_WITH_USERS, Role.class)
                    .setParameter("role", role)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by name method for " + Role.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }



    //todo
    //использовался в jdbc для вывода юзера вместе с ролями сразу в юзер дао(то есть тут он не нужен будет но пока пуска висит)
    @Override
    public List<Role> getRolesByUserId(long id) {
        return null;
    }

    //todo this method was needed for jdbc
    @Override
    public void addUserRoles(User user) {

    }

    @Override
    public void deleteAllUserRoles(long userId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.createNativeQuery("delete from ROLE_USER where user_id = :id")
                    .setParameter("id", userId).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Optional.ofNullable(entityManager).map(EntityManager::getTransaction).
                    ifPresent(EntityTransaction::rollback);

            e.printStackTrace();
            System.err.println("Error process delete all user roles method for " + Role.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
