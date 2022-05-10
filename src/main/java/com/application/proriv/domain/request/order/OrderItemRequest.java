package com.application.proriv.domain.request.order;

import lombok.Getter;
import lombok.Setter;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 19/08/2021
 * @project - proriv
 */

@Getter
@Setter
public class
OrderItemRequest {
  // Not using for OrderCreateRequest
  private Long orderItemId;

  private Long productId;
  private int quantity;
  private float price;
  private int exchange;
  private int returnProduct;
}

