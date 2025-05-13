package com.so.wallet.dto;

public class UtilisateurProfilDTO {
    
    private Long id;

    private String nom;
    private String prenom;
    private String email;

    public UtilisateurProfilDTO(Long id,String nom, String prenom, String email) {
        this.id= id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
