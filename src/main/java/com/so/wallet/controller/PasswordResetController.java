package com.so.wallet.controller;

import com.so.wallet.entities.PasswordResetToken;
import com.so.wallet.entities.Utilisateur;
import com.so.wallet.repository.PasswordResetTokenRepository;
import com.so.wallet.repository.UtilisateurRepository;
import com.so.wallet.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/forgot-password")
    public ResponseEntity<?> demanderReset(@RequestParam String email) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(email);
        if (utilisateurOpt.isEmpty()) return ResponseEntity.badRequest().body("Utilisateur introuvable");

        Utilisateur utilisateur = utilisateurOpt.get();
        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUtilisateur(utilisateur);
        resetToken.setExpiration(LocalDateTime.now().plusMinutes(30));
        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:4200/reset-password?token=" + token;
        emailService.envoyerMail(utilisateur.getEmail(), "Réinitialisation de mot de passe",
                "Cliquez sur ce lien pour réinitialiser votre mot de passe : " + resetLink);

        return ResponseEntity.ok("Un lien de réinitialisation a été envoyé à votre email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestParam String nouveauMotDePasse) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isEmpty() || tokenOpt.get().getExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token invalide ou expiré");
        }

        Utilisateur utilisateur = tokenOpt.get().getUtilisateur();
        utilisateur.setPassword(passwordEncoder.encode(nouveauMotDePasse));
        utilisateurRepository.save(utilisateur);

        tokenRepository.delete(tokenOpt.get());

        return ResponseEntity.ok("Mot de passe modifié avec succès");
    }
}

