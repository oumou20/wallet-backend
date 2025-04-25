package com.so.wallet.repository;

import com.so.wallet.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUtilisateurIdOrderByDateNotificationDesc(Long utilisateurId);

    boolean existsByUtilisateurIdAndMessageAndDateNotificationBetween(Long utilisateurId, String message, LocalDateTime start, LocalDateTime end);
}
