package com.so.wallet.entities;

import jakarta.persistence.*;
import lombok.Data;
<<<<<<< HEAD
import org.springframework.context.annotation.Import;
=======
>>>>>>> origin/main

import java.time.LocalDateTime;

@Entity
@Data
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiration;


    @OneToOne
    @JoinColumn(name = "utilisateur_id", unique = true)

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
=======
    private String token;
    private LocalDateTime expiration;

    @OneToOne
    @JoinColumn(name = "utilisateur_id", unique = true)
>>>>>>> origin/main
    private Utilisateur utilisateur;

}

