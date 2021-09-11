package com.application.proriv.domain.request.customer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 22/08/2021
 * @project - proriv
 */

@Getter
@Setter
public class AddPhoneToCustomerRequest {
  private Long customerId;

  @Pattern(regexp = "^(\\+7|8)\\d{10}$")
  private String phone;
}
