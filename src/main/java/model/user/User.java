package model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.grade.Grade;
import model.group.Group;
import model.membership.Membership;
import model.membership.MembershipType;
import model.parenthood.Parenthood;
import model.subject.Subject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "users", schema = "gestorDatabase", catalog = "gestorDatabase")
public class User {

    @OneToOne(mappedBy = "user")
    private Parenthood parenthood;

    @OneToOne
    @JoinColumn(name = "membershipId", insertable = false, updatable = false)
    private Membership membership;

    @OneToMany(mappedBy = "user")
    private Set<Grade> grades;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "groups_has_users",
            joinColumns = @JoinColumn(name = "users_userId"),
            inverseJoinColumns = @JoinColumn(name = "groups_groupId"))
    private Set<Group> groups = new HashSet();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "users_has_subjects",
            joinColumns = @JoinColumn(name = "users_userId"),
            inverseJoinColumns = @JoinColumn(name = "subjects_subjectId"))
    private Set<Subject> subjects = new HashSet();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;
    private String surname;
    private String email;
    private String password;
    private int membershipId; //TODO remove this field and use only Membership object


    public User(String name, String surname, String email, String password, MembershipType membershipType) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.membershipId = membershipType.getMembershipTypeId();
    }

    public void addGroup(Group group) {
        this.groups.add(group);
        group.getUsers().add(this);
    }

    public void removeGroup(Group group) {
        this.groups.add(group);
        group.getUsers().remove(this);
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getUsers().add(this);
    }

    public void removeSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getUsers().remove(this);
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    public void removeGrade(Grade grade) {
        this.grades.remove(grade);
    }

}
