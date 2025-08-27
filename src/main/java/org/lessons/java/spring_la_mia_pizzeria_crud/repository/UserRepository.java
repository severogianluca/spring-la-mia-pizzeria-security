package org.lessons.java.spring_la_mia_pizzeria_crud.repository;

import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    // gestisco con l'optional in caso cè o non cè un username
    Optional<User> findByUsername(String username);
    
}
