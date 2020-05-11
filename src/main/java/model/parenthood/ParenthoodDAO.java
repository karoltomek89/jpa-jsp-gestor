package model.parenthood;

import java.util.List;

public interface ParenthoodDAO {

    void save(int firstPrentId);

    void update(Parenthood parenthood);

    void delete(String id);

    Parenthood find(String id);

    List<Parenthood> findAll();

}
