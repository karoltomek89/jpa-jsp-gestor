package com.kt.jspjpa.gestor.model.membership;

public enum MembershipType {

    STUDENT(1),
    TEACHER(2),
    PARENT(3),
    DIRECTOR(4),
    TEST(5),
    UNSET(0); //TODO remove unset

    private final int membershipTypeId;

    MembershipType(int membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public int getMembershipTypeId() {
        return membershipTypeId;
    }

}
