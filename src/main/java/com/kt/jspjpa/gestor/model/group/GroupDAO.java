package com.kt.jspjpa.gestor.model.group;

import java.util.List;

public interface GroupDAO {

//    void register(String s);

    void save(String name);

    void update(Group group);

    void delete(String id);

    Group find(String id);

    List<Group> findAll();

}
