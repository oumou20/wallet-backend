package com.so.wallet.service;

import com.so.wallet.entities.Budget;
import com.so.wallet.entities.PasswordResetToken;
import com.so.wallet.entities.Rapport;
import com.so.wallet.entities.Utilisateur;
import com.so.wallet.repository.*;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtilisateurService {
  @Autowired
  BudgetRepository budgetRepository;
  @Autowired
  RapportRepository rapportRepository;
  @Autowired
  private  UtilisateurRepository utilisateurRepository;
    @Autowired
    private final PasswordResetTokenRepository tokenRepository;
    @Autowired
    private final EmailService emailService;

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

//ajout pour mot de pass oublié
public void sendResetPasswordToken(String email) {
    Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Aucun utilisateur trouvé avec cet email"));

    String token = UUID.randomUUID().toString();
    LocalDateTime expiration = LocalDateTime.now().plusMinutes(15);

    PasswordResetToken resetToken = tokenRepository.findByUtilisateur(utilisateur)
            .orElse(new PasswordResetToken());

    resetToken.setToken(token);
    resetToken.setExpiration(expiration);
    resetToken.setUtilisateur(utilisateur);

    tokenRepository.save(resetToken);

    String resetLink = "http://localhost:4200/reset-password?token=" + token; // Flutter changera ça
    emailService.sendResetPasswordEmail(email, resetLink);
}

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Jeton invalide"));

        if (resetToken.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Jeton expiré");
        }

        Utilisateur utilisateur = resetToken.getUtilisateur();
        utilisateur.setPassword(newPassword); // tu peux encoder ici
        utilisateurRepository.save(utilisateur);

        tokenRepository.delete(resetToken); // sécurité : on supprime le token après usage
    }
}

