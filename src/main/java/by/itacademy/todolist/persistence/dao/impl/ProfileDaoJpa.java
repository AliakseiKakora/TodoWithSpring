package by.itacademy.todolist.persistence.dao.impl;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.persistence.dao.ProfileDao;
import by.itacademy.todolist.persistence.exception.DaoException;

import javax.persistence.EntityManager;

public class ProfileDaoJpa extends AbstractDaoJpa<Profile> implements ProfileDao<Profile> {

    private static final String EXIST_LOGIN_AND_EMAIL = "select exists (select u.id from users u " +
            "left join profiles p on u.id = p.id " +
            "where login =:login or email =:email)";

    @Override
    public boolean existLoginOrEmail(String login, String email) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return (Boolean) entityManager.createNativeQuery(EXIST_LOGIN_AND_EMAIL)
                    .setParameter("email", email)
                    .setParameter("login", login).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error process exist login or email method for " + Profile.class.getSimpleName() + " - " + e.getMessage());
            throw new DaoException("Error process find by id", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}