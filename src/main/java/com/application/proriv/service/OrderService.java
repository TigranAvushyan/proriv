package com.application.proriv.service;


import com.application.proriv.domain.model.Product;
import com.application.proriv.domain.model.customer.Customer;
import com.application.proriv.domain.model.order.Order;
import com.application.proriv.domain.model.order.OrderItem;
import com.application.proriv.domain.request.OrderChangeRequest;
import com.application.proriv.domain.request.OrderCreateRequest;
import com.application.proriv.repository.ProductRepository;
import com.application.proriv.repository.order.OrderItemRepository;
import com.application.proriv.repository.order.OrderRepository;
import com.application.proriv.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.application.proriv.enums.OrderStatus.ACCEPTED;


@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;
  private final CustomerService customerService;

  public Order createOrder(Customer customer) {
    Order order = Order.builder()
        .date(new Date())
        .orderStatus(ACCEPTED)
        .customer(customer)
        .build();
    orderRepository.save(order);
    return order;
  }

  public List<Order> getAllOrder() {
    return orderRepository.findAll();
  }

  @Transactional
  public void addOrderItems(OrderCreateRequest orderCreateRequest) {
    Customer customer = customerService.getCustomerById(orderCreateRequest.getCustomerId());
    Order order = createOrder(customer);
    orderCreateRequest.getOrderItems().forEach(item -> {
      Long productId = item.getProductId();
      Product product = productRepository.findProductById(productId)
          .orElseThrow(() -> new EntityNotFoundException("Product not found"));

      OrderItem orderItem = OrderItem.builder()
          .order(order)
          .quantity(item.getQuantity())
          .price(item.getPrice())
          .product(product)
          .returnProduct(item.getReturnProduct())
          .exchange(item.getExchange())
          .build();
      orderItemRepository.save(orderItem);
    });
  }

  public void changeOrder(OrderChangeRequest changeRequest) {
    changeRequest.getOrderItems().forEach(item -> {
      OrderItem orderItem = orderItemRepository.findOrderItemById(item.getOrderItemId());

      if (item.getQuantity() != 0)
        orderItem.setQuantity(item.getQuantity());
      orderItem.setReturnProduct(item.getReturnProduct());
      orderItem.setExchange(item.getExchange());

      orderItemRepository.save(orderItem);
    });
  }
}
