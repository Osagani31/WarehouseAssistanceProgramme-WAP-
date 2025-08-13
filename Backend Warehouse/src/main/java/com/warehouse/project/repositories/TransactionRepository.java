package com.warehouse.project.repositories;

import com.warehouse.project.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaRepository<com.warehouse.project.models.Transactions, Long>,
        JpaSpecificationExecutor<Transactions> {

}
