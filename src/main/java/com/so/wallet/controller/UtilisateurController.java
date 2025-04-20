package com.so.wallet.controller;

import com.so.wallet.entities.Utilisateur;
import com.so.wallet.repository.UtilisateurRepository;
import  com.so.wallet.service.UtilisateurService;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
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
    public ResponseEntity<Utilisateur> updatedUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateurDetails) {
        Utilisateur existingUtilisateur = utilisateurService.findById(id);

        if (existingUtilisateur != null) {
            existingUtilisateur.setPassword(utilisateurDetails.getPassword());
            existingUtilisateur.setEmail(utilisateurDetails.getEmail());

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
}
