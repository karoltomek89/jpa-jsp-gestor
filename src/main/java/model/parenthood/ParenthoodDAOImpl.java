package model.parenthood;

import model.GeneralDAO;

import java.util.List;

public class ParenthoodDAOImpl implements ParenthoodDAO {
    GeneralDAO generalDAO = new GeneralDAO();

    @Override
    public void save(int firstPrentId) {
        Parenthood parenthood = new Parenthood(firstPrentId);
        generalDAO.save(parenthood);
    }

    @Override
    public void update(Parenthood parenthood) {
        generalDAO.update(parenthood);
    }

    @Override
    public void delete(String id) {
        generalDAO.deleteById(Parenthood.class, id);
    }

    @Override
    public Parenthood find(String id) {
        Parenthood parenthood = generalDAO.find(Parenthood.class, id);
        return parenthood;
    }

    @Override
    public List<Parenthood> findAll() {
        List<Parenthood> parenthoodList = generalDAO.findAll(Parenthood.class);
        return parenthoodList;
    }
}
