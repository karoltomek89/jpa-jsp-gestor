package com.kt.jspjpa.gestor.model.grade;

import java.util.List;

public interface GradeDAO {

    void save(int value, int studentsStudentId, int subjectsSubjectId);

    void update(Grade g);

    void delete(String id);

    Grade find(String id);

    List<Grade> findAll();

    List<Grade> findAllByStudentId(int studentId);

    List<GradeWithSubjectName> findAllByStudentIdWithName(int studentId);
}
