package com.so.wallet.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private boolean vue = false;

    private LocalDateTime dateNotification;

    @ManyToOne
    private Utilisateur utilisateur;
}
