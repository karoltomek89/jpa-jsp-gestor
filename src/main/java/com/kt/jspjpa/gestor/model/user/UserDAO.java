package com.kt.jspjpa.gestor.model.user;

import com.kt.jspjpa.gestor.model.membership.MembershipType;

import java.util.List;

public interface UserDAO {

    void register(String name, String surname, String email, String password, MembershipType membershipType);

    void save(User user);

    void update(User user);

    void delete(String userId);

    User findById(String userId);

    int login(String email, String password);

    List<User> findAll();

    List<User> findAllByMembershipType(MembershipType membershipType);

    String getEmail(String userId);

    int getMembershipTypeId(int userId);

    MembershipType getMembershipTypeById(int membershipTypeId);

    int getUserId(User user);

    int getUserGroupId(String userId);

    List<User> findAllByGroup(String groupId);
}
