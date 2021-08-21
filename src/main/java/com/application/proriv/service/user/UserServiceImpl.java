package com.application.proriv.service.user;

import com.application.proriv.domain.model.user.Authority;
import com.application.proriv.domain.model.user.Role;
import com.application.proriv.domain.model.user.User;
import com.application.proriv.domain.request.CreateUserRequest;
import com.application.proriv.enums.UserStatus;
import com.application.proriv.repository.user.AuthorityRepository;
import com.application.proriv.repository.user.RoleRepository;
import com.application.proriv.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Set;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 08/08/2021
 * @project - Proriv
 */


@Slf4j
@Service("userServiceImpl")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final AuthorityRepository authorityRepository;
  private final BCryptPasswordEncoder pEncoder;


  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public User getByUsername(String username) {
    User user = userRepository
      .findByUsername(username)
      .orElseThrow(
        () -> new UsernameNotFoundException("User by name " + username + " doesn't exist")
      );
    log.info("Got user by username: {}", user.getUsername());
    return user;
  }

  @Override
  public Role saveRole(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public void addPhone(String phone, String username) {
    User user = getByUsername(username);
    user.getPhone().add(phone);
    save(user);
  }

  @Override
  public Authority saveAuthority(Authority authority) {
    return authorityRepository.save(authority);
  }

  @Override
  public Role getRole(String name) {
    return roleRepository.findByName(name);
  }

  @Override
  public Authority getAuthority(String name) {
    return authorityRepository.findByName(name).orElse(null);
  }

  @Override
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @Override
  public User create(
    CreateUserRequest r
  ) {
    if (userRepository.existsByUsername(r.getUsername())) {
      throw new EntityExistsException("User by username is "+ r.getUsername() +" already exists");
    }
    Set<Role> role = Set.of(roleRepository.findByName("ROLE_USER"));

    return userRepository.save(
      User.builder()
          .username(r.getUsername())
          .password(pEncoder.encode(r.getPassword()))
          .firstName(r.getFirstName())
          .lastName(r.getLastName())
          .phone(List.of(r.getPhone()))
          .isEnabled(true)
          .userStatus(UserStatus.ACTIVE)
          .roles(role)
          .build()
    );
  }
}
