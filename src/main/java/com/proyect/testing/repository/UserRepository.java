package com.proyect.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyect.testing.model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByUsername(String username);


}
