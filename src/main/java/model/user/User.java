package model.user;

import model.membership.MembershipType;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "gestorDatabase", catalog = "gestorDatabase")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;

    private String name;
    private String surname;
    private String email;
    private String password;

    @Column(name = "membershipId")
    private int membershipId;

    public User() {
    }

    public User(String name, String surname, String email, String password, MembershipType membershipType) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.membershipId = membershipType.getMembershipTypeId();
    }


    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(MembershipType membershipType) {
        this.membershipId = membershipType.getMembershipTypeId();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", membership=" + membershipId +
                '}';
    }
}
