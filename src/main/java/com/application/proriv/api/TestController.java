package com.application.proriv.api;

import com.application.proriv.domain.model.Order;
import com.application.proriv.domain.model.User;
import com.application.proriv.domain.request.OrderRequest;
import com.application.proriv.service.OrderService;
import com.application.proriv.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class TestController {

  private final OrderService orderService;
  private final UserService userService;


  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @PostMapping("/order")
  public void addOrder(@RequestBody Set<OrderRequest> orderRequests) {
    Order order = orderService.createOrder();
    orderService.addOrderItemToOrder(order, orderRequests);
  }
}
