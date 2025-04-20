package com.so.wallet.repository;

import com.so.wallet.entities.Rapport;
import com.so.wallet.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RapportRepository extends JpaRepository<Rapport, Long> {
    List<Rapport> findByUtilisateurId(Long utilisateurId);

}
