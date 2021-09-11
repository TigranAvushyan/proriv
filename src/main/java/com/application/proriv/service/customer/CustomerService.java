package com.application.proriv.service.customer;

import com.application.proriv.domain.model.customer.Customer;

import java.util.List;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 17/08/2021
 * @project - proriv
 */
public interface CustomerService {

  List<Customer> getCustomers();

  Customer getCustomerById(Long id);

  Customer getCustomerByName(String name);

  void addPhone(Long id, String phone);

  void createCustomer(Customer customer);

  public List<String> wo();

}
