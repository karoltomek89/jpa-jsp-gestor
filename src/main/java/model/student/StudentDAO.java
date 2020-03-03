package model.student;

import java.util.List;

public interface StudentDAO {

    void register(String name, String surname, String email, String password, int acces_accesId);

    void save(Student s);

    void update (Student p);

    void delete (String id);

    Student find (String id);

    int login (String email, String password);

    List<Student> findAll();
}
