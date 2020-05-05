package model.parenthood;

import model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "parenthood", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Parenthood {

    @OneToOne
    @JoinColumn(name = "users_userId", insertable = false, updatable = false)
    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parenthoodId")
    private int parenthoodId;

    @Column(name = "users_userId")
    private int userId;

    @Column(name = "users_first_parent_Id")
    private int firstPrentId;

    @Column(name = "users_second_parent_Id")
    private int secondPrentId;

    public Parenthood() {
    }

    public Parenthood(int userId, int firstPrentId) {
        this.userId = userId;
        this.firstPrentId = firstPrentId;
    }

    public int getParenthoodId() {
        return parenthoodId;
    }

    public void setParenthoodId(int parenthoodId) {
        this.parenthoodId = parenthoodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFirstPrentId() {
        return firstPrentId;
    }

    public void setFirstPrentId(int firstPrentId) {
        this.firstPrentId = firstPrentId;
    }

    public int getSecondPrentId() {
        return secondPrentId;
    }

    public void setSecondPrentId(int secondPrentId) {
        this.secondPrentId = secondPrentId;
    }
}
