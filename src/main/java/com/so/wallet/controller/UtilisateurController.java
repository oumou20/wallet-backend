package com.so.wallet.controller;

import com.so.wallet.dto.ResetPasswordRequest;
import com.so.wallet.dto.UtilisateurProfilDTO;
import com.so.wallet.entities.Utilisateur;
import com.so.wallet.repository.UtilisateurRepository;
import  com.so.wallet.service.UtilisateurService;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    private final UtilisateurRepository utilisateurRepository;
    @Autowired
    private final UtilisateurService utilisateurService;


    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateur() {
        return new ResponseEntity<>(utilisateurRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Utilisateur> createPerson(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurCreated = utilisateurRepository.save(utilisateur);
        return new ResponseEntity<>(utilisateurCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.findById(id);
        return utilisateur != null ? new ResponseEntity<>(utilisateur, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updatedUtilisateur(@PathVariable Long id,
                                                          @RequestBody Utilisateur utilisateurDetails,
                                                          @AuthenticationPrincipal Utilisateur utilisateur ) {
        Utilisateur existingUtilisateur = utilisateurService.findById(id);

        if (existingUtilisateur != null) {
            existingUtilisateur.setName(utilisateurDetails.getName());
            existingUtilisateur.setEmail(utilisateurDetails.getEmail());
            existingUtilisateur.setPrenom(utilisateurDetails.getPrenom()); 


            Utilisateur updatedUtilisateur = utilisateurService.saveOrUpdate(existingUtilisateur);
            return new ResponseEntity<>(updatedUtilisateur, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        Utilisateur utilisateur = utilisateurService.findById(id);

        if (utilisateur != null) {
            utilisateurService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/profil")
    public ResponseEntity<UtilisateurProfilDTO> getProfilUtilisateur(@AuthenticationPrincipal Utilisateur utilisateur) {
        UtilisateurProfilDTO profilDTO = new UtilisateurProfilDTO(
                utilisateur.getId(),
                utilisateur.getName(),
                utilisateur.getPrenom(),
                utilisateur.getEmail()
        );
        return ResponseEntity.ok(profilDTO);
    }

//ajout pour mot de pass oublié
@PostMapping("/forgot-password")
public ResponseEntity<String> forgotPassword(@RequestParam String email) {
    utilisateurService.sendResetPasswordToken(email);
    return ResponseEntity.ok("Lien de réinitialisation envoyé (console pour test)");
}

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        utilisateurService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Mot de passe réinitialisé avec succès");
    }
}
