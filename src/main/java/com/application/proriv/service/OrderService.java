package com.application.proriv.service;


import com.application.proriv.domain.model.Order;
import com.application.proriv.domain.model.OrderItem;
import com.application.proriv.domain.model.Product;
import com.application.proriv.domain.request.OrderRequest;
import com.application.proriv.repository.order.OrderItemRepository;
import com.application.proriv.repository.order.OrderRepository;
import com.application.proriv.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.application.proriv.enums.OrderStatus.*;


@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;


  public OrderService(
    OrderRepository orderRepository, OrderItemRepository orderItemRepository,
    ProductRepository productRepository
  ) {
    this.orderRepository = orderRepository;
    this.orderItemRepository = orderItemRepository;
    this.productRepository = productRepository;
  }

  public Order createOrder() {
    Order order = new Order();
    order.setOrderStatus(ACCEPTED);
    order.setDate(new Date());
    orderRepository.save(order);
    return order;
  }

  public void saveItem(OrderItem orderItem) {
    orderItemRepository.save(orderItem);
  }

  public List<Order> getAllOrder() {
    return orderRepository.findAll();
  }

  public void addOrderItemToOrder(Order order, Set<OrderRequest> orderRequests) {
    orderRequests.forEach(orderRequest -> {
      Product product = productRepository.findById(orderRequest.getProductId())
                                         .orElseThrow();

      OrderItem orderItem = new OrderItem();
      orderItem.setOrder(order);
      orderItem.setProduct(product);
      orderItem.setQuantity(orderRequest.getQuantity());
      orderItemRepository.save(orderItem);
    });
  }
}
