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


@Entity
@Table(name = "authorities")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToMany(mappedBy = "authorities")
  @JsonIgnore
  private Set<Role> roles;
}
