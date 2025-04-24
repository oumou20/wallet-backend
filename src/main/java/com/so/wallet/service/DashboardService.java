package com.so.wallet.service;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    // Suppose que tu as un service de transactions ou de finances qui peut te fournir ces données
    private final TransactionService transactionService;

    // Injecter les autres services nécessaires si besoin (par exemple, pour accéder aux catégories ou à d'autres données)
    public DashboardService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Récupérer le solde total
    public double getSoldeTotal() {
        double totalIncome = transactionService.getTotalIncome();   // Tu dois créer une méthode pour obtenir les revenus
        double totalExpense = transactionService.getTotalExpense(); // Et une autre pour les dépenses
        return totalIncome - totalExpense;  // Le solde est la différence entre revenus et dépenses
    }

    // Récupérer les statistiques des dépenses (ex : dépenses mensuelles, totaux)
    public Object getStatistiques() {
        // Logique pour récupérer les statistiques
        return transactionService.getMonthlyExpensesStatistics();  // Exemple d'appel à un autre service pour obtenir les stats mensuelles
    }

    // Récupérer la répartition des dépenses (camemberts)
    public Object getRepartition() {
        return transactionService.getCategoryWiseExpenses();  // Exemple de méthode pour obtenir les dépenses par catégorie
    }
}
