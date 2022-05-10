package com.application.proriv.repository.user;

import com.application.proriv.domain.model.user.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 11/08/2021
 * @project - proriv
 */

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  Optional<Authority> findByName(String name);
}
