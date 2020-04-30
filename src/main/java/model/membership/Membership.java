package model.membership;

import javax.persistence.*;

@Entity
@Table(name = "membership")
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membershipId")
    private int membershipId;

    private MembershipType type;

    private String comment;

    public Membership() {
    }

    public Membership(int membershipId, MembershipType type, String comment) {
        this.membershipId = membershipId;
        this.type = type;
        this.comment = comment;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public MembershipType getType() {
        return type;
    }

    public void setType(MembershipType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
