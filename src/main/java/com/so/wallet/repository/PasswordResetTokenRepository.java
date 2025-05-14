package com.so.wallet.repository;

import com.so.wallet.entities.PasswordResetToken;
import com.so.wallet.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findByUtilisateur(Utilisateur utilisateur);
}
