package com.so.wallet.service;

import com.so.wallet.entities.Transaction;
import com.so.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
    public List<Transaction> filtrerParDate(LocalDate debut, LocalDate fin) {
        return transactionRepository.findByDateBetween(debut, fin);
    }
    public List<Transaction> filtrerParMontant(double min, double max) {
        return transactionRepository.findByMontantBetween(min, max);
    }
    public List<Transaction> filtrerParCategorie(Long categorieId) {
        return transactionRepository.findByCategorieId(categorieId);
    }


}
