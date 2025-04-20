package com.so.wallet.service;

import com.so.wallet.entities.Rapport;
import com.so.wallet.entities.Transaction;
import com.so.wallet.repository.RapportRepository;
import com.so.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RapportService {

    @Autowired
    private RapportRepository rapportRepository;
    TransactionRepository transactionRepository;

    public List<Rapport> getAllRapports() {
        return rapportRepository.findAll();
    }

    public Optional<Rapport> getRapportById(Long id) {
        return rapportRepository.findById(id);
    }

    public Rapport saveRapport(Rapport rapport) {
        return rapportRepository.save(rapport);
    }

    public void deleteRapport(Long id) {
        rapportRepository.deleteById(id);
    }

    public Map<String, Double> genererContenu(Long utilisateurId) {
        List<Transaction> transactions = transactionRepository.findByUtilisateurId(utilisateurId);

        Map<String, Double> contenu = new HashMap<>();

        for (Transaction t : transactions) {
            String categorie = t.getCategorie().getNom();
            contenu.put(categorie, contenu.getOrDefault(categorie, 0.0) + t.getMontant());
        }

        return contenu;
    }

}
