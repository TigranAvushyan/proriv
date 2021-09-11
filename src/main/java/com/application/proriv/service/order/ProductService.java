package com.application.proriv.service.order;

import com.application.proriv.domain.model.Product;

import java.util.List;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 22/08/2021
 * @project - proriv
 */

public interface ProductService {
  List<Product> getAll();

  Product getById(Long id);
}
