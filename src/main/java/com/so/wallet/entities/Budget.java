package com.so.wallet.entities;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mois;
    private Double montant;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;


    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    @JsonIgnore 
    private List<Transaction> transactions;


}
