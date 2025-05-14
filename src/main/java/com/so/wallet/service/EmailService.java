package com.so.wallet.service;


/*import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
*/
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    public void sendResetPasswordEmail(String to, String resetLink) {
        // Pour le PFE, affiche le lien en console pour tester
        System.out.println("Lien de réinitialisation envoyé à : " + to);
        System.out.println("Lien : " + resetLink);
    }
}

