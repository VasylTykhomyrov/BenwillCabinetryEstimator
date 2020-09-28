package com.benwillcabinets.benwillestimator.service;

import com.benwillcabinets.benwillestimator.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductService extends JpaRepository<Product,Integer> {

    Optional<Product> findByName(String productName);
}
