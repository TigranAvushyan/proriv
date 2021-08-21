package com.application.proriv.repository.user;

import com.application.proriv.domain.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 08/08/2021
 * @project - proriv
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
