package com.palo.backend.service;

import com.palo.backend.domain.Product;
import com.palo.backend.exceptions.NotFoundException;
import com.palo.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<Product> findAll(Integer pageNumber, Integer pageSize, String sortBy) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, Direction.ASC, sortBy));
    }

    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Product %s not found", id)));
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }


    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public Product update(Integer id, Product product) {
        findById(id);
        product.setId(id);
        return productRepository.save(product);
    }


}
