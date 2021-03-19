package by.itacademy.todolist.persistence.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManagerFactoryUtil {

    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static  {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    public static void shutdown() {
        if (ENTITY_MANAGER_FACTORY != null) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }
}
