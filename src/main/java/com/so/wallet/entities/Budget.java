package com.so.wallet.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private Double montant;

    @ManyToOne
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Transaction> transactions;


}
