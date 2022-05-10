package com.application.proriv.service.order;

import com.application.proriv.domain.model.customer.Customer;
import com.application.proriv.domain.model.order.Order;
import com.application.proriv.domain.request.order.OrderChangeRequest;
import com.application.proriv.domain.request.order.OrderCreateRequest;

import java.util.List;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 22/08/2021
 * @project - proriv
 */
public interface OrderService {

  Order createOrder(Customer customer);

  List<Order> getAllOrder();

  void addOrderItems(OrderCreateRequest orderCreateRequest);

  void changeOrder(OrderChangeRequest changeRequest);

}
