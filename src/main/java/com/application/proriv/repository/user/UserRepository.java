package com.application.proriv.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import com.application.proriv.domain.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
  boolean existsByUsername(String username);
}
