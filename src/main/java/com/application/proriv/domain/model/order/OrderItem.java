package com.application.proriv.domain.model.order;

import com.application.proriv.domain.model.Product;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "price")
  private float price;

  @Column(name = "exchange")
  private int exchange;

  @Column(name = "return_product")
  private int returnProduct;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @OneToOne
  @JoinColumn(name = "product_id")
  private Product product;

}
