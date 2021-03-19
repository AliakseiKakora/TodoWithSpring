package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.persistence.dao.CrudDao;
import by.itacademy.todolist.persistence.exception.DaoException;
import by.itacademy.todolist.persistence.util.JpaEntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDaoJpa<T> implements CrudDao<T> {

    protected final EntityManagerFactory entityManagerFactory = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    private final Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    public T getById(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.find(clazz, id);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + clazz.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<T> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(String.format("from %s", clazz.getSimpleName()), clazz).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find all for " + clazz.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find all", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public T save(T t) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return t;
        } catch (Exception e) {
            Optional.ofNullable(entityManager).map(EntityManager::getTransaction).
                    ifPresent(EntityTransaction::rollback);

            e.printStackTrace();
            System.err.println("Error process save method for " + clazz.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process save method", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public T update(T t) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(t);
            entityManager.getTransaction().commit();
            return t;
        } catch (Exception e) {
            Optional.ofNullable(entityManager).map(EntityManager::getTransaction).
                    ifPresent(EntityTransaction::rollback);

            e.printStackTrace();
            System.err.println("Error process update method for " + clazz.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process update method", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void deleteById(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            T t = entityManager.find(clazz, id);
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Optional.ofNullable(entityManager).map(EntityManager::getTransaction)
                    .ifPresent(EntityTransaction::rollback);

            e.printStackTrace();
            System.err.println("Error process delete method for " + clazz.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process delete method", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}