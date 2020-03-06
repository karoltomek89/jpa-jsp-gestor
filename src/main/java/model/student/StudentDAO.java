package model.student;

import java.util.List;

public interface StudentDAO {

    void register(String name, String surname, String email, String password, int acces_accesId);

    void save(Student s);

    void update(Student s);

    void delete(String id);

    Student find(String id);

    int login(String email, String password);

    List<Student> findAll();

    String getEmail(String id);
}
