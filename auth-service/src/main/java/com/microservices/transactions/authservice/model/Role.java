package com.microservices.transactions.authservice.model;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by nikola on 10/17/18.
 */
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id",nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonBackReference
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(
                    name = "role_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "permission_id"))
    @JsonManagedReference
    private Collection<Permission> permissions;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<Permission> permissions) {
        this.permissions = permissions;
    }
}
