package com.example.app.service;

import com.example.app.entity.Product;
import com.example.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllCustomers() {

        return productRepository.findAll();
    }

    public Product saveDepartment(Product product) {

        return productRepository.save(product);
    }

    public Product getDepartmentById(Long id) {
        Optional<Product> departmentFound = productRepository.findById(id);

        if (!departmentFound.isPresent()) {
            throw new NoSuchElementException("No such department");
        }

        return departmentFound.get();

    }
}
