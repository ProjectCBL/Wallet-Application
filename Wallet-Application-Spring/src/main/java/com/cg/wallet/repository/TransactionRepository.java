package com.cg.wallet.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.wallet.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	@Query("SELECT t FROM Transaction t WHERE customer_id_fk=:customerId")
	public List<Transaction> getAllTransactionsFromCustomer(@Param("customerId") Integer customerId);
	
	@Query("SELECT t FROM Transaction t WHERE customer_id_fk=:customerId and DATE(dateOfTransaction)=:date")
	public List<Transaction> getTransactionsAtDate(@Param("customerId") Integer customerId, @Param("date") Date dateOfTransaction);
	
}
