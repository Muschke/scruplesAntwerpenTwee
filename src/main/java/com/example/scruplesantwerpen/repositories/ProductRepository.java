package com.example.scruplesantwerpen.repositories;

import com.example.scruplesantwerpen.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product,Long> {
}
