package com.so.wallet.controller;

import com.so.wallet.entities.Utilisateur;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/protected")
    public String testProtected(@AuthenticationPrincipal Utilisateur utilisateur) {
        return "Bienvenue, tu es bien authentifiée 🎉";
    }
}
