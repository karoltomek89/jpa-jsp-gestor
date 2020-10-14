package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class GeneralDAO {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager = null;

    public GeneralDAO() {
        this.entityManagerFactory = EntityManagerFactoryStaticBlockSingleton.getFactory();
    }

    public <T> void update(T entity) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public <T> T find(Class<T> type, String id) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            T entity = entityManager.find(type, Integer.valueOf(id));
            transaction.commit();
            return entity;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public <T> void save(T entity) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public <T> void delete(T entity) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public <T> void deleteById(Class<T> type, String id) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            T entity = entityManager.find(type, id);
            entityManager.remove(entity);
            transaction.commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public <T> List<T> findAll(Class<T> type) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            String from = "from " + type.getName();
            List<T> entities = entityManager.createQuery(from, type).getResultList();
            transaction.commit();
            return entities;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
