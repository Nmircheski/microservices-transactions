package com.microservices.transactions.authservice.dao;


import com.microservices.transactions.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by nikola on 10/22/18.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);
}
