package com.kt.jspjpa.gestor.model.membership;

import com.kt.jspjpa.gestor.model.databases.GeneralDAO;

import java.util.List;

public class MembershipDAOImpl implements MembershipDAO {
    GeneralDAO generalDAO = new GeneralDAO();

    @Override
    public void save(MembershipType type, String comment) {
        Membership membership = new Membership(type, comment);
        generalDAO.save(membership);
    }

    @Override
    public void update(Membership membership) {
        generalDAO.update(membership);
    }

    @Override
    public void delete(String id) {
        generalDAO.deleteById(Membership.class, id);
    }

    @Override
    public Membership find(String id) {
        Membership membership = generalDAO.find(Membership.class, id);
        return membership;
    }

    @Override
    public List<Membership> findAll() {
        List<Membership> membershipList = generalDAO.findAll(Membership.class);
        return membershipList;
    }
}
