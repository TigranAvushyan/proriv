package com.application.proriv.repository.order;

import com.application.proriv.domain.model.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 08/08/2021
 * @project - Proriv
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
  OrderItem findOrderItemById(Long id);
}
