package com.springpracticesdemo.model;

import java.util.Set;

import com.springpracticesdemo.enums.LdapGroup;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
