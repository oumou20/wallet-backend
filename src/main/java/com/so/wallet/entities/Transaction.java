package com.so.wallet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.time.LocalDate;

import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

@Entity
@Data

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double montant;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TypeTransaction type;

    @ManyToOne
    private Budget budget;

    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id") // nom de la colonne dans la BDD
    private Utilisateur utilisateur;


    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
