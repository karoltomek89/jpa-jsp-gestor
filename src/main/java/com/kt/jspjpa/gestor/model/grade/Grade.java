package com.kt.jspjpa.gestor.model.grade;

import com.kt.jspjpa.gestor.model.databases.EntityManagerFactoryStaticBlockSingleton;
import com.kt.jspjpa.gestor.model.subject.Subject;
import com.kt.jspjpa.gestor.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
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

    public Grade(Double value) {
        this.value = value;
    }

}
