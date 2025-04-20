package com.so.wallet.repository;

import com.so.wallet.entities.Budget;
import com.so.wallet.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUtilisateurId(Long utilisateurId);

}
