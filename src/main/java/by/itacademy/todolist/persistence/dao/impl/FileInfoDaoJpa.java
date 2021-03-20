package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistence.dao.FileInfoDao;
import by.itacademy.todolist.persistence.exception.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public class FileInfoDaoJpa extends AbstractDaoJpa<FileInfo> implements FileInfoDao<FileInfo> {

    @Override
    public FileInfo addFileInfoForTask(FileInfo fileInfo, long task_id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            Task task = entityManager.find(Task.class, task_id);
            entityManager.getTransaction().begin();

            fileInfo.addTask(task);
            entityManager.persist(fileInfo);
            entityManager.getTransaction().commit();
            return fileInfo;
        } catch (Exception e) {
            Optional.ofNullable(entityManager).map(EntityManager::getTransaction).
                    ifPresent(EntityTransaction::rollback);

            e.printStackTrace();
            System.err.println("Error process update method for " + FileInfo.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process update method", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
