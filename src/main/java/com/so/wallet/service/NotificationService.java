package com.so.wallet.service;

import com.so.wallet.entities.Notification;
import com.so.wallet.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationsByUtilisateurId(Long utilisateurId) {
        return notificationRepository.findByUtilisateurIdOrderByDateNotificationDesc(utilisateurId);
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public boolean hasRecentBudgetAlert(Long utilisateurId, String message) {
        // Option anti-spam : vérifier s’il y a déjà une alerte identique aujourd’hui
        return notificationRepository.existsByUtilisateurIdAndMessageAndDateNotificationBetween(
                utilisateurId, message,
                java.time.LocalDateTime.now().withHour(0).withMinute(0),
                java.time.LocalDateTime.now().withHour(23).withMinute(59)
        );
    }
}
