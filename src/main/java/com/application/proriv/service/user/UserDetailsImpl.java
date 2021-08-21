package com.application.proriv.service.user;

import com.application.proriv.domain.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 08/08/2021
 * @project - Proriv
 */


public class UserDetailsImpl implements UserDetails {

  private final String username;
  private final String password;
  private final boolean isEnabled;
  private final Set<SimpleGrantedAuthority> authorities;

  public UserDetailsImpl(
    String username, String password, boolean isEnabled,
    Set<SimpleGrantedAuthority> authorities
  ) {
    this.username = username;
    this.password = password;
    this.isEnabled = isEnabled;
    this.authorities = authorities;
  }

  public static UserDetails fromUser(User user) {
    return new org.springframework.security.core.userdetails.User(
      user.getUsername(),
      user.getPassword(),
      user.isEnabled(),
      true,true,true,
      user.getAuthorities()
    );
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }
}
