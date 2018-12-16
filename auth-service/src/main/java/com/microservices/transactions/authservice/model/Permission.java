package com.microservices.transactions.authservice.model;

import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permission_id")
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "permissions")
    @JsonBackReference
    private Collection<Role> roles;

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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
