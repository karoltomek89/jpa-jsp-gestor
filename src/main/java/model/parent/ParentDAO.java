package model.parent;

import java.util.List;

public interface ParentDAO {

    void save(Parent p);

    void update (Parent p);

    void delete (String id);

    Parent find (String id);

    List<Parent> findAll();

}
