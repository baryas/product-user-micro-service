package com.example.app.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name = "product_name")
    private String productName ;

    @Column(name = "product_price")
    private String productPrice;

    @Column(name = "specification")
    private String productSpecification;

    @Column(name = "is_department_open")
    private Boolean isProductAvailable = Boolean.TRUE ;

}
