package com.so.wallet.service;

import com.so.wallet.entities.Budget;
import com.so.wallet.entities.Rapport;
import com.so.wallet.entities.Utilisateur;
import com.so.wallet.repository.TransactionRepository;
import com.so.wallet.repository.UtilisateurRepository;
import com.so.wallet.repository.BudgetRepository;
import com.so.wallet.repository.RapportRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
  @Autowired
  BudgetRepository budgetRepository;
  @Autowired
  RapportRepository rapportRepository;
  @Autowired
  private  UtilisateurRepository utilisateurRepository;


    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur findById(Long id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur saveOrUpdate(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }
    public List<Budget> getBudgets(Long utilisateurId) {

        return budgetRepository.findByUtilisateurId(utilisateurId);
    }

    public List<Rapport> getRapports(Long utilisateurId) {
        return rapportRepository.findByUtilisateurId(utilisateurId);
    }
    public Utilisateur getUtilisateurActuel() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }


}
