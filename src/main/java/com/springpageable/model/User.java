package com.springpageable.model;

import com.springpageable.enums.LdapGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private boolean isInternal;

    @ElementCollection(targetClass = LdapGroup.class)
    @CollectionTable(name = "user_ldap_groups", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "ldap_group_id")
    private Set<LdapGroup> ldapGroups;
}
