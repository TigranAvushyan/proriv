package com.application.proriv.service.customer;

import com.application.proriv.domain.model.customer.Customer;
import com.application.proriv.repository.AddressRepository;
import com.application.proriv.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 17/08/2021
 * @project - proriv
 */


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final AddressRepository addressRepository;
  private final CustomerRepository customerRepository;

  @Override
  public List<Customer> getCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Customer getCustomerById(Long id) {
    return customerRepository.findCustomerById(id);
  }

  @Override
  public Customer getCustomerByName(String name) {
    return customerRepository.findCustomerByName(name);
  }

  @Override
  public void addPhone(Long id, String phone) {
    Customer customer = customerRepository.findCustomerById(id);
    customer.getPhone().add(phone);
    customerRepository.save(customer);
  }

  @Transactional
  @Override
  public void createCustomer(Customer customer) {
    addressRepository.save(customer.getAddress());
    customerRepository.save(customer);
  }

  @Override
  public List<String> wo() {
    List<String> customers = customerRepository.customerWithoutOrder();
    return customers;
  }
}
