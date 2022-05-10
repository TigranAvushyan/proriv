package com.application.proriv.service.order;

import com.application.proriv.domain.model.Product;
import com.application.proriv.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 22/08/2021
 * @project - proriv
 */

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public List<Product> getAll() {
    return productRepository.findAll();
  }

  @Override
  public Product getById(Long id) {
    return productRepository.findProductById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
  }
}
