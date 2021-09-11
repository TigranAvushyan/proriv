package com.application.proriv.domain.response;

import com.application.proriv.domain.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 22/08/2021
 * @project - proriv
 */


@Getter
@Setter
@AllArgsConstructor
public class OrderTableResponse {

  private List<Customer> customers;

}
