package com.application.proriv.service.user;


import com.application.proriv.domain.model.Authority;
import com.application.proriv.domain.model.Role;
import com.application.proriv.domain.model.User;
import com.application.proriv.domain.request.CreateUserRequest;

import javax.persistence.EntityExistsException;
import java.util.List;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 08/08/2021
 * @project - Proriv
 */


public interface UserService {
  User save(User user);

  Role saveRole(Role role);

  Authority saveAuthority(Authority authority);

  Role getRole(String name);

  Authority getAuthority(String name);

  User getByUsername(String username);

  List<User> getUsers();

  User create(CreateUserRequest request) throws EntityExistsException;

}
