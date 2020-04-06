package model.subject;

import java.util.List;

public interface SubjectDAO {

    void register(String s);

    void save(Subject s);

    void update(Subject s);

    void delete(String id);

    Subject find(String id);

    List<Subject> findAll();

    List<Subject> findAllByUserId(String id);
}
