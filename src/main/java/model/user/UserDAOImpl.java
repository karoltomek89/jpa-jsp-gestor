package model.user;

import model.EntityManagerFactoryStaticBlockSingleton;
import model.GeneralDAO;
import model.SQLSessionFactory;
import model.membership.MembershipType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

    GeneralDAO generalDAO = new GeneralDAO();
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager = null;

    public UserDAOImpl() {
        this.entityManagerFactory = EntityManagerFactoryStaticBlockSingleton.getFactory();
    }

    @Override
    public void register(String name, String surname, String email, String password, MembershipType membershipType) {
        User newUser = new User(name, surname, email, password, membershipType);
        save(newUser);
        logger.info("User registered");
    }

    @Override
    public void save(User u) {
        generalDAO.save(u);
    }

    @Override
    public void update(User u) {
        generalDAO.update(u);
    }

    @Override
    public void delete(String id) {
        generalDAO.deleteById(User.class, id);
    }

    @Override
    public User findById(String id) {
        User user = generalDAO.find(User.class, id);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> list = generalDAO.findAll(User.class);
        return list;
    }

    @Override
    public List<User> findAllByMembershipType(MembershipType membershipType) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List<User> list = entityManager.createQuery("SELECT u FROM User u WHERE u.membershipId = :membershipId")
                    .setParameter("membershipId", membershipType.getMembershipTypeId())
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
    public int login(String email, String password) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            int userId;
            userId = (Integer) entityManager.createQuery("SELECT u.userId FROM User u WHERE u.email = :email AND u.password = :password")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            transaction.commit();
            return userId;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public String getEmail(String id) {
        return findById(id).getEmail();
    }

    @Override
    public int getMembershipTypeId(int userId) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            int membershipTypeId;
            membershipTypeId = (Integer) entityManager.createQuery("SELECT u.membershipId FROM User u WHERE u.userId = :userId")
                    .setParameter("userId", userId)
                    .getSingleResult();
            transaction.commit();
            return membershipTypeId;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public MembershipType getMembershipTypeById(int membershipTypeId) {
        switch (membershipTypeId) {
            case 1:
                return MembershipType.STUDENT;
            case 2:
                return MembershipType.TEACHER;
            case 3:
                return MembershipType.PARENT;
            case 4:
                return MembershipType.DIRECTOR;
            default:
                return MembershipType.UNSET;
        }
    }

    @Override
    public int getUserId(User user) {
        return user.getUserId();
    }

    @Override
    public int getUserGroupId(String userId) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            int groupId;
            groupId = entityManager.createQuery("SELECT g.groupId FROM Group g JOIN g.users u WHERE u.userId = :userId")
                    .setParameter("userId", Integer.valueOf(userId))
                    .getFirstResult();
            transaction.commit();
            return groupId;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<User> findAllByGroup(String groupId) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            List<User> userList = entityManager.createQuery("SELECT u FROM User u JOIN u.groups g WHERE g.groupId= :groupId")
                    .setParameter("groupId", Integer.valueOf(groupId))
                    .getResultList();
            transaction.commit();
            return userList;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
