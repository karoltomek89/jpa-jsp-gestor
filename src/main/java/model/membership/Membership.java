package model.membership;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "membership", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Membership {

    @Id
    @Column(name = "membershipId")
    private int membershipId;

    private String type;

    private String comment;

    public Membership() {
    }

    public Membership(MembershipType type, String comment) {

        this.type = type.name();
        this.comment = comment;
        this.membershipId = type.getMembershipTypeId();
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getType() {
        return type;
    }

    public void setType(MembershipType type) {
        this.type = type.name();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
