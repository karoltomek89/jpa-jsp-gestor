package model.grade;

import model.EntityManagerFactoryStaticBlockSingleton;
import model.subject.Subject;
import model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Table(name = "grades", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Grade {

    private static Logger logger = LoggerFactory.getLogger(EntityManagerFactoryStaticBlockSingleton.class);

    @PostPersist
    public void logAdd() {
        logger.info("Grade added");
    }

    @PostUpdate
    public void logUpdate() {
        logger.info("Grade updated");
    }

    @PostRemove
    public void logRemove() {
        logger.info("Grade removed");
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subjects_subjectId")
    private Subject subject;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;

    private double value;

    public Grade() {
    }

    public Grade(Double value) {
        this.value = value;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", value=" + value +
                '}';
    }
}
