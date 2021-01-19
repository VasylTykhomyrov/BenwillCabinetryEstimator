package com.benwillcabinets.benwillestimator.service;

import com.benwillcabinets.benwillestimator.domain.Transaction;
import com.benwillcabinets.benwillestimator.refacing.RefacingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionService extends JpaRepository<Transaction,Integer> {

}
