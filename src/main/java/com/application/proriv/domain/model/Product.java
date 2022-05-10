package com.application.proriv.domain.model;

import com.application.proriv.domain.model.order.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private int price;

  @OneToOne(mappedBy = "product")
  @JsonIgnore
  private OrderItem orderItem;
}
