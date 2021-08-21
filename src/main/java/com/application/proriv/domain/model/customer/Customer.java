package com.application.proriv.domain.model.customer;

import com.application.proriv.domain.model.Address;
import com.application.proriv.domain.model.order.Order;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

@Table(name = "customer")
@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      joinColumns = @JoinColumn(name = "customer_phone")
  )
  private List<String> phone;

  @Column(name = "name")
  private String name;


  @OneToOne
  @JoinTable(
      name = "customer_address",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "address_id")
  )
  private Address address;


  @OneToMany(mappedBy = "customer")
  private List<Order> orders;

}
