package com.cg.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.wallet.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
