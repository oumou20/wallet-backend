package com.so.wallet.service;


import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.so.wallet.entities.PasswordResetToken;
import com.so.wallet.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.so.wallet.entities.Utilisateur;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailService {
    @Autowired
    private PasswordResetTokenRepository tokenRepository;


    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.sender.email}")
    private String senderEmail;

    public String sendEmail(String to, String subject, String body) throws IOException {
        Email from = new Email(senderEmail);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, toEmail, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            return "Status Code: " + response.getStatusCode();
        } catch (IOException ex) {
            throw new IOException("Erreur lors de l'envoi de l'e-mail", ex);
        }
    }

    public void sendResetLink(Utilisateur utilisateur) throws IOException {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(15);

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setExpiration(expiration);
        resetToken.setUtilisateur(utilisateur);
        tokenRepository.save(resetToken);

        String link = "http://localhost:8080/reset-password?token=" + token;

        String subject = "Réinitialisation de mot de passe";
        String body = "Cliquez sur ce lien pour réinitialiser votre mot de passe : " + link;

        sendEmail(utilisateur.getEmail(), subject, body);
    }
}

