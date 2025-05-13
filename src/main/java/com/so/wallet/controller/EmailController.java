package com.so.wallet.controller;
import com.so.wallet.entities.Utilisateur;
import com.so.wallet.repository.UtilisateurRepository;
import com.so.wallet.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @PostMapping("/send")
    public String sendEmail(@RequestBody Map<String, String> body) {
        String to = body.get("to");
        String subject = body.get("subject");
        String content = body.get("body");

        try {
            return emailService.sendEmail(to, subject, content);
        } catch (IOException e) {
            return "Erreur : " + e.getMessage();
        }
    }

    @PostMapping("/reset-password")
    public String sendResetPasswordEmail(@RequestBody Map<String, String> body) {
        String email = body.get("email");

        try {
            Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByEmail(email);

            if (optionalUtilisateur.isEmpty()) {
                return "Aucun utilisateur trouvé avec cet email.";
            }

            Utilisateur utilisateur = optionalUtilisateur.get();
            emailService.sendResetLink(utilisateur);

            return "Lien de réinitialisation envoyé avec succès à " + email;
        } catch (IOException e) {
            return "Erreur : " + e.getMessage();
        }
    }


}
