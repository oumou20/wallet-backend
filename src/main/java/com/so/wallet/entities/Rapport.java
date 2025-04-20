package com.so.wallet.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Rapport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateGeneration;
    private Double totalDepense;
    private Double totalRevenu;
    private Double solde;

    @ManyToOne
    private Utilisateur utilisateur;

}
