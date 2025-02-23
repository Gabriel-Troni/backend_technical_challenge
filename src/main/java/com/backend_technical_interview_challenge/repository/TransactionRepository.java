package com.backend_technical_interview_challenge.repository;

import com.backend_technical_interview_challenge.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
