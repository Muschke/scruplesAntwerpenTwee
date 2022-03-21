package com.example.scruplesantwerpen.service;

import com.example.scruplesantwerpen.domain.Product;
import com.example.scruplesantwerpen.exceptions.ProductNietGevondenException;
import com.example.scruplesantwerpen.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class DefaultProductService implements ProductService {
    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void genereerBarcode(long id) {
        try {
            productRepository.findById(id)
                    .orElseThrow(ProductNietGevondenException::new)
                    .generateAndSetEAN13BarcodeImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
