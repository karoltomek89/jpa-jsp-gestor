package com.kt.jspjpa.gestor.model.membership;

import com.kt.jspjpa.gestor.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "membership", schema = "gestorDatabase", catalog = "gestorDatabase")
public class Membership {

    @OneToOne(mappedBy = "membership")
    private User user;

    @Id
    private int membershipId;

    private String type;

    private String comment;

    public Membership(MembershipType type, String comment) {
        this.type = type.name();
        this.comment = comment;
        this.membershipId = type.getMembershipTypeId();
    }
}
