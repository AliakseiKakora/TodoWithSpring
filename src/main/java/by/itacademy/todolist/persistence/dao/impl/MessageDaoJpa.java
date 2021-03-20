package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.persistence.dao.MessageDao;
import by.itacademy.todolist.persistence.exception.DaoException;

import javax.persistence.EntityManager;
import java.util.List;

public class MessageDaoJpa extends AbstractDaoJpa<Message> implements MessageDao<Message> {

    private static final String GET_ALL_WITH_USERS = "select distinct m from Message m " +
            "left join fetch m.user";

    private static final String GET_BY_ID_WITH_USERS = GET_ALL_WITH_USERS + " where m.id = :id";

    @Override
    public Message getById(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(GET_BY_ID_WITH_USERS, Message.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find by id method for " + Message.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Message> getAll() {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(GET_ALL_WITH_USERS , Message.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process find all for " + Message.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find all", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    //todo it need for jdbc???
    @Override
    public void deleteAllUserMessage(long userId) {

    }
}
