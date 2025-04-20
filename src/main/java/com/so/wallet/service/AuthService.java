package com.so.wallet.service;

import com.so.wallet.dto.AuthRequest;
import com.so.wallet.dto.AuthResponse;
import com.so.wallet.dto.RegisterRequest;
import com.so.wallet.entities.Utilisateur;
import com.so.wallet.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.so.wallet.security.JwtUtil;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setName(request.getName());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRole(request.getRole());
        utilisateur.setPassword(passwordEncoder.encode(request.getPassword()));

        utilisateurRepository.save(utilisateur);

        String token = jwtUtil.generateToken(utilisateur.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(request.getEmail());

        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            if (passwordEncoder.matches(request.getPassword(), utilisateur.getPassword())) {
                String token = jwtUtil.generateToken(utilisateur.getEmail());
                return new AuthResponse(token);
            }
        }

        throw new RuntimeException("Identifiants incorrects");
    }
}
