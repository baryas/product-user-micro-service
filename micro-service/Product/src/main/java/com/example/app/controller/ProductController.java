package com.example.app.controller;

import com.example.app.entity.Product;
import com.example.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> getAllCustomers (){

        return productService.getAllCustomers() ;
    }

    @PostMapping("/")
    public Product saveDepartment (@RequestBody Product product){

        return productService.saveDepartment(product);
    }

    @GetMapping("/{id}")
    public Product getDepartmentById (@PathVariable("id") Long id){

        return productService.getDepartmentById(id);
    }
}
