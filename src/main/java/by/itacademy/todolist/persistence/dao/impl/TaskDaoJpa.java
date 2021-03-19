package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistence.dao.TaskDao;
import by.itacademy.todolist.persistence.exception.DaoException;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskDaoJpa extends AbstractDaoJpa<Task> implements TaskDao<Task> {


    //todo need for jdbc
    @Override
    public Task createTaskForUser(Task task, long userId) {
        return null;
    }

    @Override
    public List<Task> getAllUserTasks(long userId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery("select t from Task t where t.user.id =:id", Task.class)
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
}
