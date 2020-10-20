package com.kt.jspjpa.gestor.model.subject;

import com.kt.jspjpa.gestor.model.databases.EntityManagerFactoryStaticBlockSingleton;
import com.kt.jspjpa.gestor.model.grade.Grade;
import com.kt.jspjpa.gestor.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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

    public Subject(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

}
