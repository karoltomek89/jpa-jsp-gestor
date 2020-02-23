package model.teacher;

import model.subject.Subject;

import java.util.List;

public interface TeacherDAO {

    void save(Teacher t);

    void update (Teacher t);

    void delete (String id);

    Teacher find (String id);

    List<Teacher> findAll();
}
