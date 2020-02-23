package model.grade;

import java.util.List;

public interface GradeDAO {

    void save(Grade g);

    void update (Grade g);

    void delete (String id);

    Grade find (String id);

    List<Grade> findAll();
}
