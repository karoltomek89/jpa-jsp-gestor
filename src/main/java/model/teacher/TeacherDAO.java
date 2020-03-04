package model.teacher;

import java.util.List;

public interface TeacherDAO {

    void register(String name, String surname, String email, String password, int acces_accesId);

    void save(Teacher t);

    void update(Teacher t);

    void delete(String id);

    Teacher find(String id);

    int login(String email, String password);

    List<Teacher> findAll();

    String getEmail(String id);
}
