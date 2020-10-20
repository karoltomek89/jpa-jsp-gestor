package com.kt.jspjpa.gestor.model.subject;

import com.kt.jspjpa.gestor.model.databases.EntityManagerFactoryStaticBlockSingleton;
import com.kt.jspjpa.gestor.model.databases.GeneralDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    GeneralDAO generalDAO = new GeneralDAO();
    private final EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager = null;

    public SubjectDAOImpl() {
        this.entityManagerFactory = EntityManagerFactoryStaticBlockSingleton.getFactory();
    }

    @Override
    public void register(String name) {
        Subject newSubject = new Subject(name);
        save(newSubject);
    }

    @Override
    public void save(Subject s) {
        generalDAO.save(s);
    }

    @Override
    public void update(Subject s) {
        generalDAO.update(s);
    }

    @Override
    public void delete(String id) {
        generalDAO.deleteById(Subject.class, id);
    }

    @Override
    public Subject find(String id) {
        Subject subject = generalDAO.find(Subject.class, id);
        return subject;
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> list = generalDAO.findAll(Subject.class);
        return list;
    }

    @Override
    public List<Subject> findAllByUserId(String userId) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List<Subject> list = entityManager.createQuery("SELECT s FROM Subject s JOIN s.users u WHERE u.userId = :userId")
                    .setParameter("userId", Integer.valueOf(userId))
                    .getResultList();
            transaction.commit();
            return list;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
