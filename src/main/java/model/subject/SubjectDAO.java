package model.subject;

import model.student.Student;

import java.util.List;

public interface SubjectDAO {

    void save(Subject s);

    void update (Subject p);

    void delete (String id);

    Subject find (String id);

    List<Subject> findAll();
}
