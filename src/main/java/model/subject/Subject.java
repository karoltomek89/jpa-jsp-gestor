package model.subject;

import model.EntityManagerFactoryStaticBlockSingleton;
import model.grade.Grade;
import model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Subject {

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

    @ManyToMany(mappedBy = "subjects")
    private Set<User> users = new HashSet();

    @OneToMany(mappedBy = "subject")
    private Set<Grade> grades = new HashSet();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;

    private String name;

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }


    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", name='" + name + '\'' +
                '}';
    }
}
