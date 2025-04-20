package com.so.wallet.repository;

import com.so.wallet.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByBudgetId(Long budgetId);

    List<Transaction> findByUtilisateurId(Long utilisateurId);

    List<Transaction> findByDateBetween(LocalDate debut, LocalDate fin);

    List<Transaction> findByMontantBetween(double min, double max);

    List<Transaction> findByCategorieId(Long categorieId);
}
