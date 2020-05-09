package model.group;

import model.SQLSessionFactory;
import model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Group {

    private static Logger logger = LoggerFactory.getLogger(SQLSessionFactory.class);

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

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                '}';
    }
}
