package com.application.proriv.domain.request.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class OrderCreateRequest {
  private Long customerId;
  private Set<OrderItemRequest> orderItems;
}
