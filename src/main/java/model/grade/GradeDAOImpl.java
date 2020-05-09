package model.grade;

import model.EntityManagerFactoryStaticBlockSingleton;
import model.GeneralDAO;
import model.subject.Subject;
import model.user.User;
import org.hibernate.transform.ResultTransformer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class GradeDAOImpl implements GradeDAO {

    GeneralDAO generalDAO = new GeneralDAO();
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager = null;

    public GradeDAOImpl() {
        this.entityManagerFactory = EntityManagerFactoryStaticBlockSingleton.getFactory();
    }

    @Override //TODO how to use GeneralDAO save method?
    public void save(int value, int userId, int subjectsSubjectId) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            Grade grade = new Grade((double) value);
            User user = entityManager.find(User.class, userId);
            Subject subject = entityManager.find(Subject.class, subjectsSubjectId);
            grade.setSubject(subject);
            grade.setUser(user);
            entityManager.persist(grade);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(Grade grade) {
        generalDAO.update(grade);
    }

    @Override
    public void delete(String id) {
        generalDAO.deleteById(Grade.class, id);
    }

    @Override
    public Grade find(String id) {
        Grade grade = generalDAO.find(Grade.class, id);
        return grade;
    }

    @Override
    public List<Grade> findAll() {
        List<Grade> grades = generalDAO.findAll(Grade.class);
        return grades;
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
            List<GradeWithSubjectName> list = entityManager.createQuery(
                    "SELECT g.value, g.gradeId, s.name  FROM Grade g " +
                            "JOIN Subject s ON s.subjectId = g.subject.subjectId " +
                            "WHERE g.user.userId = :studentId")
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
