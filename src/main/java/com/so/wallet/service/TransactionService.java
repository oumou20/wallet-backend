package com.so.wallet.service;

import com.so.wallet.entities.Transaction;
import com.so.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> filtrerParDate(LocalDate debut, LocalDate fin) {
        return transactionRepository.findByDateBetween(debut, fin);
    }

    public List<Transaction> filtrerParMontant(double min, double max) {
        return transactionRepository.findByMontantBetween(min, max);
    }

    public List<Transaction> filtrerParCategorie(Long categorieId) {
        return transactionRepository.findByCategorieId(categorieId);
    }

    // 1. Méthode pour récupérer le total des revenus
    public double getTotalIncome() {
        return transactionRepository.findByType("REVENUE").stream()
                .mapToDouble(Transaction::getMontant)
                .sum();
    }

    // 2. Méthode pour récupérer le total des dépenses
    public double getTotalExpense() {
        return transactionRepository.findByType("EXPENSE").stream()
                .mapToDouble(Transaction::getMontant)
                .sum();
    }

    // 3. Méthode pour récupérer les dépenses mensuelles
    public List<Object> getMonthlyExpensesStatistics() {
        // Exemples d'agrégations pour obtenir les totaux mensuels des dépenses
        return Collections.singletonList(transactionRepository.findMonthlyExpenseStatistics()); // Tu peux créer une requête personnalisée pour ça
    }

    // 4. Méthode pour récupérer les dépenses par catégorie
    public List<Object> getCategoryWiseExpenses() {
        // Exemples d'agrégations pour obtenir les dépenses par catégorie
        return Collections.singletonList(transactionRepository.findCategoryWiseExpenseDistribution()); // Création d'une méthode dans le repo pour l'agrégation
    }
}
