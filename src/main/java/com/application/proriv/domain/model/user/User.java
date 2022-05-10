package com.application.proriv.domain.model.user;


import com.application.proriv.enums.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      name = "user_phone",
      joinColumns = @JoinColumn(name = "user_id")
  )
  private List<String> phone;

  private boolean isEnabled;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private UserStatus userStatus;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles;

  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
      role.getAuthorities().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getName())));
    });
    return authorities;
  }
}
