package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistence.dao.TaskDao;
import by.itacademy.todolist.persistence.exception.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public class TaskDaoJpa extends AbstractDaoJpa<Task> implements TaskDao<Task> {

    private static final String GET_ALL_USER_TASKS = "select distinct t from Task t " +
            "left join fetch t.fileInfo where t.user.id =:id";

    private static final String DELETE_ALL_USER_TASKS = "delete from Task t where t.user.id = :id";

    private static final String GET_BY_ID = "select t from Task t left join fetch t.fileInfo where t.id = :id";

    private static final String DELETE_MARKED_AS_DELETED_TASKS = "delete from Task t where t.isDeleted = true";

    @Override
    public List<Task> getAllUserTasks(long userId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(GET_ALL_USER_TASKS, Task.class)
                    .setParameter("id", userId).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find all user task for " + Task.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find all", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteAllUserTasks(long userId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.createQuery(DELETE_ALL_USER_TASKS)
                    .setParameter("id", userId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Optional.ofNullable(entityManager).map(EntityManager::getTransaction)
                    .ifPresent(EntityTransaction::rollback);

            e.printStackTrace();
            System.err.println("Error process delete method for " + Task.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process delete method", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Task getById(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(GET_BY_ID, Task.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + Task.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteUserTasksMarkedAsDeleted(long userId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.createQuery(DELETE_MARKED_AS_DELETED_TASKS).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Optional.ofNullable(entityManager).map(EntityManager::getTransaction)
                    .ifPresent(EntityTransaction::rollback);

            e.printStackTrace();
            System.err.println("Error process delete method for " + Task.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process delete method", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
