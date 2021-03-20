package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistence.dao.UserDao;
import by.itacademy.todolist.persistence.exception.DaoException;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDaoJpa extends AbstractDaoJpa<User> implements UserDao<User> {

    private static final String GET_BY_ID = "select distinct u from User u " +
            "join fetch u.roles " +
            "join fetch u.profile " +
            "where u.id = :id";

    private static final String GET_BY_LOGIN_AND_PASSWORD = "select distinct u from User u " +
            "join fetch u.roles " +
            "join fetch u.profile p " +
            "where p.login = :login and p.password =:password";

    @Override
    public User getById(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(GET_BY_ID, User.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + User.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<User> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery("select distinct u from User u " +
                    "join fetch u.roles " +
                    "join fetch u.profile", User.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find all for " + User.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find all", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(GET_BY_LOGIN_AND_PASSWORD, User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + User.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

}