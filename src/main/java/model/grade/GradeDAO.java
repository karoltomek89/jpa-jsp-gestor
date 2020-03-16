package model.grade;

import java.util.List;

public interface GradeDAO {

    void save(int value, int students_studentId, int subjects_subjectId);

    void update(Grade g);

    void delete(String id);

    Grade find(String id);

    List<Grade> findAll();

    List<Grade> findAllByStudentId(int studentId);

    List<GradeWithSubjectName> findAllByStudentIdWithName(int studentId);
}
