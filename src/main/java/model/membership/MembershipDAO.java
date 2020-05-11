package model.membership;

import java.util.List;

public interface MembershipDAO {


    void save(MembershipType type, String comment);

    void update(Membership membership);

    void delete(String id);

    Membership find(String id);

    List<Membership> findAll();

}
