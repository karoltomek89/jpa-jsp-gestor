package com.kt.jspjpa.gestor.model.grade;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class GradeWithSubjectName {
    private int gradeId;
    private double value;
    private String name;

    public GradeWithSubjectName(int gradeId, double value, String name) {
        this.gradeId = gradeId;
        this.value = value;
        this.name = name;
    }

}
