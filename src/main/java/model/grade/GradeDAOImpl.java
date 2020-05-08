package model.grade;

import model.EntityManagerFactoryStaticBlockSingleton;
import model.SQLSessionFactory;
import model.subject.Subject;
import model.user.User;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class GradeDAOImpl implements GradeDAO {

    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

    private EntityManagerFactory entityManagerFactory = null;

    private EntityManager entityManager = null;

    public GradeDAOImpl() {
        this.entityManagerFactory = EntityManagerFactoryStaticBlockSingleton.getFactory();
    }

    @Override
    public void save(int value, int userId, int subjectsSubjectId) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Grade grade = new Grade((double) value);
            grade.setSubject(entityManager.find(Subject.class, subjectsSubjectId));
            grade.setUser(entityManager.find(User.class, userId));

            entityManager.persist(grade);

            transaction.commit();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(Grade grade) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.merge(grade);

            transaction.commit();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(String id) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Grade grade = entityManager.find(Grade.class, id);
            entityManager.remove(grade);

            transaction.commit();

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Grade find(String id) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Grade grade = entityManager.find(Grade.class, id);

            transaction.commit();
            return grade;

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Grade> findAll() {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            List<Grade> grades = entityManager.createQuery("from Grade", Grade.class).getResultList();

            transaction.commit();
            return grades;

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Grade> findAllByStudentId(int studentId) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            List<Grade> list = entityManager.createQuery(" FROM Grade g JOIN Subject WHERE g.users_userId = :studentId")
                    .setParameter("studentId", studentId)
                    .getResultList();

            transaction.commit();
            return list;

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }

    @Override
    public List<GradeWithSubjectName> findAllByStudentIdWithName(int studentId) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            List<GradeWithSubjectName> list = entityManager.createQuery("SELECT g.value, g.gradeId, s.name  FROM Grade g JOIN Subject s ON s.subjectId = g.subject.subjectId WHERE g.user.userId = :studentId")
                    .setParameter("studentId", studentId).unwrap(org.hibernate.query.Query.class).setResultTransformer(
                            new ResultTransformer() {
                                @Override
                                public Object transformTuple(
                                        Object[] tuple,
                                        String[] aliases) {
                                    return new GradeWithSubjectName(
                                            (int) tuple[1],
                                            (double) tuple[0],
                                            (String) tuple[2]
                                    );
                                }

                                @Override
                                public List transformList(List collection) {
                                    return collection;
                                }
                            }
                    )
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
