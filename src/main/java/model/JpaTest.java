package model;

import model.grade.Grade;
import model.group.Group;
import model.membership.Membership;
import model.membership.MembershipType;
import model.parenthood.Parenthood;
import model.subject.Subject;
import model.user.User;

import javax.persistence.*;
import java.util.List;

public class JpaTest {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            /**
             * Tworzymy nowy obiekt EntityManagerFactory z konfiguracją Persistence Unit o nazwie: "pl.sda.jpa.starter"
             */
            entityManagerFactory = Persistence.createEntityManagerFactory("model");
            /**
             * tworzymy nową instancję EntityManager
             */
            entityManager = entityManagerFactory.createEntityManager();

            /**
             * Do pracy z bazą danych potrzebujemy transakcji
             */
            EntityTransaction transaction = entityManager.getTransaction();
            /**
             * Zaczynamy nową transakcję, każda operacja na bazie danych musi być "otoczona" transakcją
             */
            transaction.begin();

            /**
             * Zapisujemy encję w bazie danych
             */
            Group group = new Group("testowa");
            Membership membership = new Membership(MembershipType.TEST, "testowanko");
            Subject subject = new Subject("WF");
            User user = new User("Test", "Test", "test@test", "test@test", MembershipType.TEST);
            User user2 = new User("Test2", "Test2", "test2@test2", "test2@test2", MembershipType.TEST);

            Parenthood parenthood = new Parenthood(8, 3);
            Parenthood parenthood2 = new Parenthood(9, 3);

            Grade grade = new Grade(8.0D);
            Grade grade2 = new Grade(9.0D);

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
            user.setMembershipId(MembershipType.STUDENT);
            user2.setMembershipId(MembershipType.TEACHER);
            entityManager.merge(user);
            entityManager.merge(user2);

            user.addGroup(group);
            user2.addGroup(group);

            user.addSubject(subject);
            user2.addSubject(subject);

            grade.setSubject(subject);
            grade.setUser(user);

            grade2.setSubject(subject);
            grade2.setUser(user2);

            entityManager.persist(grade);
            entityManager.persist(grade2);

            entityManager.persist(parenthood);
            entityManager.persist(parenthood2);


            //entityManager.remove(coachEntity);

            /**
             * Wyciągamy wszystkie encje zapisane w bazie danych
             */
            TypedQuery<Group> query = entityManager.createQuery("from Group", Group.class);
            List<Group> groups = query.getResultList();
            System.out.println("groups = " + groups);

            /**
             * Kończymy (commitujemy) transakcję - wszystkie dane powinny być zapisane w bazie
             */
            transaction.commit();
        } finally {
            /**
             * Kończymy pracę z entityManager, zamykamy go i tym samym zamykamy Persistence Context z nim związany
             * Czemu EntityManager nie implementuje AutoClosable? https://github.com/javaee/jpa-spec/issues/77
             */

            if (entityManager != null) {
                entityManager.close();
                entityManagerFactory.close();
            }
        }
    }
}
