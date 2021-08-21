package com.application.proriv.domain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 08/08/2021
 * @project - Proriv
 */


@Entity @Table(name="roles")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToMany
  @JoinTable(
    name = "role_authority",
    joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "authority_id")
  )
  private Set<Authority> authorities;

  @JsonIgnore
  @ManyToMany(mappedBy = "roles")
  private Set<User> users;

}
