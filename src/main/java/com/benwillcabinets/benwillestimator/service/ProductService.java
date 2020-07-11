package com.benwillcabinets.benwillestimator.service;

import com.benwillcabinets.benwillestimator.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductService extends JpaRepository<Product,Integer> {

}
