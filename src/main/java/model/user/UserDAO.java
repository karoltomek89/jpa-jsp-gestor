package model.user;

import model.Membership;

import java.util.List;

public interface UserDAO {

    void register(String name, String surname, String email, String password, Membership membership);

    void save(User u);

    void update(User u);

    void delete(String id);

    User findByID(String id);

    int login(String email, String password);

    List<User> findAll();

    List<User> findAllByMembership(Membership membership);

    String getEmail(String id);

    Membership getMembershipById(int membershipId);

    int getUserId(User user);
}
