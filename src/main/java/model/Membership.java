package model;

public enum Membership {

    STUDENT(1),
    TEACHER(2),
    PARENT(3),
    DIRECTOR(4),
    UNSET(0);

    private int membershipId;

    Membership(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getMembershipId() {
        return membershipId;
    }

}
