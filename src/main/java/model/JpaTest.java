package model;

import model.group.Group;
import model.membership.Membership;
import model.membership.MembershipType;
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
            Group group = new Group("test2");
            Membership membership = new Membership(MembershipType.TEST, "testowanko");
            Subject subject = new Subject("WF");
            User user = new User("Test", "Test", "test@test", "test@test", MembershipType.TEST);

            entityManager.persist(group); // zapisanie do bazy danych
            entityManager.persist(membership);
            entityManager.persist(subject);
            entityManager.persist(user);

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
