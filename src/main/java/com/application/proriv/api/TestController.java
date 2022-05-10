package com.application.proriv.api;

import com.application.proriv.domain.model.user.User;
import com.application.proriv.domain.request.OrderChangeRequest;
import com.application.proriv.domain.request.OrderCreateRequest;
import com.application.proriv.service.OrderService;
import com.application.proriv.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class TestController {

  private final OrderService orderService;
  private final UserService userService;


  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @PostMapping("/create")
  public void createOrder(@RequestBody OrderCreateRequest orderCreateRequests) {
    orderService.addOrderItems(orderCreateRequests);
  }

  @PutMapping("/change")
  public void changeOrder(@RequestBody OrderChangeRequest changeRequest) {
    orderService.changeOrder(changeRequest);
  }
}
