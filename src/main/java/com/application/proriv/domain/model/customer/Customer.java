package com.application.proriv.domain.model.customer;

import com.application.proriv.domain.model.Address;
import com.application.proriv.domain.model.order.Order;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table(name = "customer")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(
      joinColumns = @JoinColumn(name = "customer_phone")
  )
  private Set<String> phone;


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
