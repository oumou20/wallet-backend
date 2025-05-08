package com.so.wallet.repository;

import com.so.wallet.entities.Budget;
import com.so.wallet.entities.Transaction;
import com.so.wallet.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUtilisateurId(Long utilisateurId);
    // Méthode pour récupérer les budgets d'un utilisateur
    List<Budget> findByUtilisateur(Utilisateur utilisateur);

}
