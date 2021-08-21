package com.application.proriv.domain.request;

import lombok.*;

import java.util.Set;


@Getter @Setter
public class OrderCreateRequest {
  private Long customerId;
  private Set<OrderItemRequest> orderItems;
}
