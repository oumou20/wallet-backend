package com.so.wallet.service;

import com.so.wallet.entities.Notification;
import com.so.wallet.entities.Transaction;
import com.so.wallet.repository.NotificationRepository;
import com.so.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    private NotificationService notificationService;

    @Autowired
    private BudgetService budgetService;


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        // Enregistrer la transaction
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Vérifier si une alerte doit être envoyée pour cette transaction
        checkAndCreateAlert(savedTransaction);

        return savedTransaction;
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

    // Méthode pour créer une notification si une transaction dépasse le budget

    public void checkAndCreateAlert(Transaction transaction) {
        if (transaction.getBudget() != null) {
            List<Transaction> transactions = transactionRepository.findByBudgetId(transaction.getBudget().getId());

            double totalDepense = transactions.stream()
                    .mapToDouble(Transaction::getMontant)
                    .sum();

            double montantBudget = transaction.getBudget().getMontant();

            if (totalDepense > montantBudget) {
                String message = "Alerte : Budget dépassé ! Dépenses actuelles : " + totalDepense + " / Budget : " + montantBudget;

                // Vérifier si une alerte similaire a déjà été envoyée aujourd’hui
                if (!notificationService.hasRecentBudgetAlert(transaction.getUtilisateur().getId(), message)) {
                    Notification notification = new Notification();
                    notification.setMessage(message);
                    notification.setDateNotification(java.time.LocalDateTime.now());
                    notification.setUtilisateur(transaction.getUtilisateur());
                    notificationService.saveNotification(notification);
                }
            }
        }
    }

}

