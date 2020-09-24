package com.benwillcabinets.benwillestimator.service;

import com.benwillcabinets.benwillestimator.domain.Product;
import com.benwillcabinets.benwillestimator.refacing.RefacingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefacingItemService extends JpaRepository<RefacingItem,Integer> {

}
