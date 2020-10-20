package model.parenthood;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.user.User;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
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

    public Parenthood(int firstPrentId) {
        this.firstPrentId = firstPrentId;
        this.secondPrentId = 0;
    }
}
