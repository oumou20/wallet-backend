package com.so.wallet.controller;
/*
import com.so.wallet.entities.PasswordResetToken;
import com.so.wallet.entities.Utilisateur;
import com.so.wallet.repository.PasswordResetTokenRepository;
import com.so.wallet.repository.UtilisateurRepository;
import com.so.wallet.service.EmailService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional; // ajouter

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController

@RequestMapping("/password-reset")
public class PasswordResetController {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetController(UtilisateurRepository utilisateurRepository,
                                   PasswordResetTokenRepository tokenRepository,
                                   EmailService emailService,
                                   PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    // Étape 1 : Demande de réinitialisation
    @PostMapping("/request")
    public ResponseEntity<String> requestReset(@RequestParam String email) {
        Optional<Utilisateur> optionalUser = utilisateurRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Utilisateur utilisateur = optionalUser.get();

            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUtilisateur(utilisateur);
            resetToken.setExpiration(LocalDateTime.now().plusMinutes(15));
            tokenRepository.save(resetToken);

            String link = "http://localhost:8080/password-reset/reset?token=" + token;
            String body = "Bonjour " + utilisateur.getName() + ",\n\n" +
                    "Cliquez sur ce lien pour réinitialiser votre mot de passe :\n" + link +
                    "\n\nCe lien expire dans 15 minutes.";

            try {
                emailService.sendEmail(utilisateur.getEmail(), "Réinitialisation de mot de passe", body);
            } catch (IOException | java.io.IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
            }

            return ResponseEntity.ok("E-mail de réinitialisation envoyé.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun utilisateur trouvé avec cet e-mail.");
        }
    }

    // Étape 2 : Réinitialisation avec le token
    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token,
                                                @RequestParam String nouveauMotDePasse) {
        Optional<PasswordResetToken> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isPresent()) {
            PasswordResetToken resetToken = optionalToken.get();

            if (resetToken.getExpiration().isBefore(LocalDateTime.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le lien a expiré.");
            }

            Utilisateur utilisateur = resetToken.getUtilisateur();
            utilisateur.setPassword(passwordEncoder.encode(nouveauMotDePasse));
            utilisateurRepository.save(utilisateur);

            tokenRepository.delete(resetToken); // Invalider le token après usage

            return ResponseEntity.ok("Mot de passe réinitialisé avec succès.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token invalide.");
        }
    }
}
*/

