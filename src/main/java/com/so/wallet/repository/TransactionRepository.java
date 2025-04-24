package com.so.wallet.repository;

import com.so.wallet.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByBudgetId(Long budgetId);

    List<Transaction> findByUtilisateurId(Long utilisateurId);

    List<Transaction> findByDateBetween(LocalDate debut, LocalDate fin);

    List<Transaction> findByMontantBetween(double min, double max);

    List<Transaction> findByCategorieId(Long categorieId);

    // 1. Méthode pour récupérer les transactions par type (revenu, dépense)
    List<Transaction> findByType(String type);

    // 2. Requête pour obtenir les dépenses mensuelles (on peux adapter selon notre modèle de données)
    @Query("SELECT EXTRACT(MONTH FROM t.date) AS month, SUM(t.montant) AS totalExpense " +
            "FROM Transaction t WHERE t.type = 'EXPENSE' GROUP BY EXTRACT(MONTH FROM t.date)")
    List<Object[]> findMonthlyExpenseStatistics(); // Retourne un tableau d'objets [mois, total]

    // 3. Requête pour obtenir les dépenses par catégorie
    @Query("SELECT t.categorie.id AS categoryId, SUM(t.montant) AS totalExpense " +
            "FROM Transaction t WHERE t.type = 'EXPENSE' GROUP BY t.categorie.id")
    List<Object[]> findCategoryWiseExpenseDistribution(); // Retourne un tableau d'objets [categorieId, total]

}
