package com.microservices.transactions.authservice.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Created by nikola on 10/17/18.
 */
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id",nullable = false, updatable = false)
    private Long id;

    @Column(name = "username",nullable = false)
    private String userName;

    @Column(name = "password",nullable = false)
    private String password;

    @Column
    private String email;

    @Column(name = "enabled",nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference
    private List<Role> roles;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (Role r : roles) {
            authorities.add(new SimpleGrantedAuthority(r.getName()));
        }

        return authorities;

    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.userName = username;
    }
}
