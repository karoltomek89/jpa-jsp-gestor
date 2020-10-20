package com.kt.jspjpa.gestor.model.group;

import com.kt.jspjpa.gestor.model.databases.GeneralDAO;

import java.util.List;

public class GroupDAOImpl implements GroupDAO {
    GeneralDAO generalDAO = new GeneralDAO();

    @Override
    public void save(String name) {
        Group group = new Group(name);
        generalDAO.save(group);
    }

    @Override
    public void update(Group group) {
        generalDAO.update(group);
    }

    @Override
    public void delete(String id) {
        generalDAO.deleteById(Group.class, id);
    }

    @Override
    public Group find(String id) {
        Group group = generalDAO.find(Group.class, id);
        return group;
    }

    @Override
    public List<Group> findAll() {
        List<Group> groupList = generalDAO.findAll(Group.class);
        return groupList;
    }
}
