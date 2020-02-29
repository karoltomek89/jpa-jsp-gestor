package model.teacher;

import model.subject.Subject;

import java.util.List;

public interface TeacherDAO {

    void register(String name, String surname, String email, String password, int acces);

    void save(Teacher t);

    void update (Teacher t);

    void delete (String id);

    Teacher find (String id);

    List<Teacher> findAll();
}
