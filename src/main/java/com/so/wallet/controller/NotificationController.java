package com.so.wallet.controller;

import com.so.wallet.entities.Notification;
import com.so.wallet.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/utilisateur/{utilisateurId}")
    public List<Notification> getNotificationsByUtilisateur(@PathVariable Long utilisateurId) {
        return notificationService.getNotificationsByUtilisateurId(utilisateurId);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}
