package com.example.scruplesantwerpen.service;

import com.example.scruplesantwerpen.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts();
    void genereerBarcode(long id);
}
