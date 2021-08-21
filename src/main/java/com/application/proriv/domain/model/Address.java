package com.application.proriv.domain.model;

import com.application.proriv.domain.model.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 17/08/2021
 * @project - proriv
 */


@Getter @Setter
@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String region;
  private String city;
  private String street;
  private String house;

  @OneToOne(mappedBy = "address")
  @JsonIgnore
  private Customer customer;
}
