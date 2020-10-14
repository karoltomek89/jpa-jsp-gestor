package model.user;

import model.grade.Grade;
import model.group.Group;
import model.membership.Membership;
import model.membership.MembershipType;
import model.parenthood.Parenthood;
import model.subject.Subject;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public User() {
    }

    public User(String name, String surname, String email, String password, MembershipType membershipType) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
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

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(MembershipType membershipType) {
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

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getUsers().add(this);
    }

    public void removeSubject(Subject subject) {
        this.subjects.add(subject);
        subject.getUsers().remove(this);
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
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

    public void removeGrade(Grade grade) {
        this.grades.remove(grade);
    }

    public Parenthood getParenthood() {
        return parenthood;
    }

    public void setParenthood(Parenthood parenthood) {
        this.parenthood = parenthood;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
