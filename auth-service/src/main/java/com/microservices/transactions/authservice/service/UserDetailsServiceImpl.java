package com.microservices.transactions.authservice.service;


import com.microservices.transactions.authservice.dao.UserRepository;
import com.microservices.transactions.authservice.model.Permission;
import com.microservices.transactions.authservice.model.Role;
import com.microservices.transactions.authservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by nikola on 10/22/18.
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        try {
            final User user = userRepository.findByUserName(username);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    // UTIL

    private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        return getGrantedAuthorities(getPermissions(roles));
    }

    private final List<String> getPermissions(final Collection<Role> roles) {
        final List<String> permissions = new ArrayList<>();
        final List<Permission> collection = new ArrayList<>();
        for (final Role role : roles) {
            collection.addAll(role.getPermissions());
        }
        for (final Permission item : collection) {
            permissions.add(item.getName());
        }

        return permissions;
    }

    private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


}
