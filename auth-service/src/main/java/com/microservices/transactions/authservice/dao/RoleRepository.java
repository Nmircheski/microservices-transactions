package com.microservices.transactions.authservice.dao;

import com.microservices.transactions.authservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
