package com.so.wallet.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AccueilController {

    @GetMapping("/")
    public String welcome() {
        return "Bienvenue sur votre application Spring Boot déployée sur Fly.io !";
    }
}
