package com.application.proriv.domain.model.order;

import com.application.proriv.domain.model.customer.Customer;
import com.application.proriv.enums.OrderStatus;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinTable(
      name = "customer_orders",
      joinColumns = @JoinColumn(name = "customer_id"),
      inverseJoinColumns = @JoinColumn(name = "order_id")
  )
  private Customer customer;

  @CreatedDate
  private Date date;

  @Column(name = "order_status")
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @OneToMany(mappedBy = "order")
  @ToString.Exclude
  private List<OrderItem> orderItems;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Order order = (Order) o;

    return Objects.equals(id, order.id);
  }
}
