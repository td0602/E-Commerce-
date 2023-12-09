package com.tmdt.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

//    Bang User va Role quan he N-N, nen ta tach lam 3 bang: User, UserRole, Role
//    User vs UserRole vs Role: 1-N-1
    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;

    public Role() {

    }

    public Role(String name, Set<UserRole> userRoles) {
        this.name = name;
        this.userRoles = userRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
