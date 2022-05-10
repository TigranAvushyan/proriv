package com.application.proriv.domain.request.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 20/08/2021
 * @project - proriv
 */

@Getter
@Setter
public class OrderChangeRequest {
  private Set<OrderItemRequest> orderItems;
}
