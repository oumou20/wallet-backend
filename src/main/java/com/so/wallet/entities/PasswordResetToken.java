package com.so.wallet.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private LocalDateTime expiration;

    @OneToOne
    @JoinColumn(name = "utilisateur_id", unique = true)
    private Utilisateur utilisateur;

}

