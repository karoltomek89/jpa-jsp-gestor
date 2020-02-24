package model.student;

import model.parent.Parent;

import java.util.List;

public interface StudentDAO {

    void register(String name, String surname, String email, String password);

    void save(Student s);

    void update (Student p);

    void delete (String id);

    Student find (String id);

    List<Student> findAll();
}
