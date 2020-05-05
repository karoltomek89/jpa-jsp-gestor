package model.subject;

import model.grade.Grade;
import model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subjects", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Subject {

    @ManyToMany
            (mappedBy = "subjects")
    private Set<User> users = new HashSet();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectId")
    private int subjectId;
    private String name;

    @OneToMany(mappedBy = "subject")
    private Set<Grade> grades = new HashSet();

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
