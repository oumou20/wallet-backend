package com.so.wallet.service;

import com.so.wallet.entities.Categorie;
import com.so.wallet.entities.Transaction;
import com.so.wallet.repository.CategorieRepository;
import com.so.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;
    TransactionRepository transactionRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> getCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
    public List<Transaction> getTransactions(Long categorieId) {
        return transactionRepository.findByCategorieId(categorieId);
    }

}
