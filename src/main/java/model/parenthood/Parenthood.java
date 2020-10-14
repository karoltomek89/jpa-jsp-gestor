package model.parenthood;

import model.user.User;

import javax.persistence.*;

@Entity
@Table(name = "parenthood", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Parenthood {

    @OneToOne
    @JoinColumn(name = "users_userId")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int parenthoodId;

    @Column(name = "users_first_parent_Id")
    private int firstPrentId;

    @Column(name = "users_second_parent_Id")
    private int secondPrentId;

    public Parenthood() {
    }

    public Parenthood(int firstPrentId) {
        this.firstPrentId = firstPrentId;
        this.secondPrentId = 0;
    }

    public int getParenthoodId() {
        return parenthoodId;
    }

    public void setParenthoodId(int parenthoodId) {
        this.parenthoodId = parenthoodId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
