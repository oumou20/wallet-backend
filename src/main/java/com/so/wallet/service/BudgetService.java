package com.so.wallet.service;

import com.so.wallet.entities.Budget;
import com.so.wallet.entities.Transaction;
import com.so.wallet.entities.TypeTransaction;
import com.so.wallet.repository.BudgetRepository;
import com.so.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
    public double calculerSolde(Long budgetId) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget introuvable"));

        List<Transaction> transactions = transactionRepository.findByBudgetId(budgetId);

        double totalDepenses = transactions.stream()
                .filter(t -> t.getType() == TypeTransaction.DEPENSE)
                .mapToDouble(Transaction::getMontant)
                .sum();

        return budget.getMontant() - totalDepenses;
    }

    public Budget ajusterBudget(Long id, Double nouveauMontant) {
        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget introuvable"));
        budget.setMontant(nouveauMontant);
        return budgetRepository.save(budget);
    }

}

