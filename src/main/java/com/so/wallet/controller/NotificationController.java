package com.so.wallet.controller;

import com.so.wallet.entities.Notification;
import com.so.wallet.entities.Utilisateur;
import com.so.wallet.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/mes-notifications")
    public List<Notification> getNotificationsByUtilisateurConnecte(@AuthenticationPrincipal Utilisateur utilisateur) {
        return notificationService.getNotificationsByUtilisateurId(utilisateur.getId());
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}
