package com.so.wallet.dto;

public class UtilisateurProfilDTO {
    private String nom;
    private String prenom;
    private String email;

    public UtilisateurProfilDTO(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }
}
