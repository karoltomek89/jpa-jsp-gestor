package model.user;

import model.Membership;

import java.util.List;

public interface UserDAO {

    void register(String name, String surname, String email, String password, Membership membership);

    void save(User u);

    void update(User u);

    void delete(String id);

    User find(String id);

    int login(String email, String password);

    List<User> findAll();

    String getEmail(String id);

    Membership getMembershipById(int membershipId);
}
