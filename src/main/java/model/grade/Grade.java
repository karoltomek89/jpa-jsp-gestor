package model.grade;

import javax.persistence.*;

@Entity
@Table(name = "grades", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gradeId")
    private int gradeId;

    private double value;

    public Grade() {
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

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", value=" + value +
                '}';
    }
}
