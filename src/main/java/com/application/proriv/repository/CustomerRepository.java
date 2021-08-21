package com.application.proriv.repository;

import com.application.proriv.domain.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findCustomerByName(String name);
  Customer findCustomerById(Long id);
}
