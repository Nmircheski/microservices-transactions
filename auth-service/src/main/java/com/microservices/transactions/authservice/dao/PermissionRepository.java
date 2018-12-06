package com.microservices.transactions.authservice.dao;

import com.microservices.transactions.authservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByName(String name);


}
