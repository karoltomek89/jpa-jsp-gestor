package model.group;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.EntityManagerFactoryStaticBlockSingleton;
import model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "groups", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Group {

    private static Logger logger = LoggerFactory.getLogger(EntityManagerFactoryStaticBlockSingleton.class);

    @PostPersist
    public void logAdd() {
        logger.info("Group added");
    }

    @PostUpdate
    public void logUpdate() {
        logger.info("Group updated");
    }

    @PostRemove
    public void logRemove() {
        logger.info("Group removed");
    }

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    private String name;

    public Group(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

}
