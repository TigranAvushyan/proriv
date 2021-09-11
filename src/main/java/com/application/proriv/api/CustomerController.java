package com.application.proriv.api;

import com.application.proriv.domain.model.customer.Customer;
import com.application.proriv.domain.request.customer.AddPhoneToCustomerRequest;
import com.application.proriv.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 22/08/2021
 * @project - proriv
 */

@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping
  public List<String> getWo() {
    return customerService.wo();
  }

  @PostMapping("/phone")
  public void addPhone(@RequestBody @Valid AddPhoneToCustomerRequest r) {
    customerService.addPhone(r.getCustomerId(), r.getPhone());
  }

  @PostMapping
  public void createCustomer(@RequestBody Customer customer) {
    customerService.createCustomer(customer);
  }
}
