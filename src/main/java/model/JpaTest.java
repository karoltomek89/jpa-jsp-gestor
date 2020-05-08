package model;

import model.grade.Grade;
import model.group.Group;
import model.membership.Membership;
import model.membership.MembershipType;
import model.parenthood.Parenthood;
import model.subject.Subject;
import model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaTest {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = null;

        EntityManager entityManager = null;

        try {
            entityManagerFactory = EntityManagerFactoryStaticBlockSingleton.getFactory();
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Group group = new Group("testowa");

            Membership membership = new Membership(MembershipType.TEST, "testowanko");

            Subject subject = new Subject("WF");

            User user = new User("Test", "Test", "test@test", "test@test", MembershipType.TEST);
            User user2 = new User("Test2", "Test2", "test2@test2", "test2@test2", MembershipType.TEST);
            user.setMembershipId(MembershipType.STUDENT);
            user2.setMembershipId(MembershipType.TEACHER);
            user.addGroup(group);
            user2.addGroup(group);
            user.addSubject(subject);
            user2.addSubject(subject);

            Grade grade = new Grade(8.0D);
            Grade grade2 = new Grade(9.0D);
            grade.setSubject(subject);
            grade.setUser(user);
            grade2.setSubject(subject);
            grade2.setUser(user2);

            Parenthood parenthood = new Parenthood(3);
            Parenthood parenthood2 = new Parenthood(3);
            parenthood.setUser(user);
            parenthood2.setUser(user2);

            entityManager.persist(group); // zapisanie do bazy danych
            entityManager.persist(subject);
            entityManager.persist(membership);
            entityManager.persist(user);
            entityManager.persist(user2);
            entityManager.remove(user);
            entityManager.remove(user2);
            entityManager.remove(membership);
            entityManager.persist(membership);
            entityManager.persist(user);
            entityManager.persist(user2);
            entityManager.merge(user);
            entityManager.merge(user2);
            entityManager.persist(grade);
            entityManager.persist(grade2);
            entityManager.persist(parenthood);
            entityManager.persist(parenthood2);

            TypedQuery<Group> query = entityManager.createQuery("from Group", Group.class);
            List<Group> groups = query.getResultList();
            System.out.println("groups = " + groups);


//            /**
//             *  krótka forma: "FROM CourseEntity"
//             */
//            Query simpleQuery = entityManager.createQuery("SELECT c FROM CourseEntity c JOIN c.students");
//            List resultList = simpleQuery.getResultList();
//
//            /**
//             *  to samo co wyżej ale używamy TypeQuery
//             */
//            TypedQuery<CourseEntity> typedQuery = entityManager.createQuery("SELECT c FROM CourseEntity c", CourseEntity.class);
//            /**
//             *  polecenie SELECT na bazie jest wykonywane dopiero przy wywołaniu metod pobierających wyniki, np.: getResultList(), getResultStream()
//             */
//            List<CourseEntity> courseEntities = typedQuery.getResultList();
//
//            /**
//             *  możemy też pobrać wyniki jako Stream, zauważ że polecenie SELECT jest ponownie wykonywane
//             */
//            typedQuery.getResultStream().forEach(entity -> logger.info("{} : {}", entity.getName(), entity.getPlace()));
//
//            /**
//             *  pobieramy tylko nazwy i miasto za pomocą NamedQuery, zapytanie jest zapisane w CourseEntity, tutaj tylko wykonujemy je po nazwie
//             */
//            simpleQuery = entityManager.createNamedQuery("CourseEntity.selectNameAndPlace");
//            resultList = simpleQuery.getResultList();
//
//            /**
//             *  sortujemy po nazwie
//             */
//            TypedQuery<Object[]> scalarQuery = entityManager.createQuery("SELECT c.name, c.place FROM CourseEntity c ORDER BY c.name ASC", Object[].class);
//            List<Object[]> scalarResultList = scalarQuery.getResultList();
//            Object[] firstRow = scalarResultList.get(0);
//            logger.info("Row 1, column 1: " + firstRow[0]);
//            logger.info("Row 1, column 2: " + firstRow[1]);
//            printList(resultList);
//
//            /**
//             *  zawężamy tylko do kursów z Sopotu i wrzucamy dane do obiektu CourseInfo
//             */
//            List<CourseInfo> courseInfoList = entityManager.createQuery("SELECT new pl.sda.jpa.starter.queries.entities.CourseInfo(c.name, c.place) FROM CourseEntity c WHERE c.place = :place", CourseInfo.class)
//                    .setParameter("place", "Sopot")
//                    .getResultList();
//            printList(courseInfoList);
//
//            /**
//             *  zawężamy tylko do kursów z Gdynia i pobieramy tylko jeden
//             */
//            typedQuery = entityManager.createQuery("SELECT c FROM CourseEntity c WHERE c.place = :place", CourseEntity.class);
//            typedQuery.setParameter("place", "Gdynia");
//            typedQuery.setMaxResults(1);
//            CourseEntity courseInGdynia = typedQuery.getSingleResult();
//            logger.info("Single result: {}", courseInGdynia);
//
//            /**
//             *  dodajemy stronicowanie
//             */
//            simpleQuery = entityManager.createQuery("FROM CourseEntity");
//            simpleQuery.setFirstResult(1);
//            simpleQuery.setMaxResults(2);
//            resultList = simpleQuery.getResultList();
//            printList(resultList);
//
//            /**
//             *  grupowanie
//             */
//            simpleQuery = entityManager.createQuery("SELECT c.place, COUNT(c) FROM CourseEntity c GROUP BY c.place");
//            resultList = simpleQuery.getResultList();
//            printList(resultList);


            transaction.commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
                entityManagerFactory.close();
            }
        }
    }
}
