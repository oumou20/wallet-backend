package com.so.wallet.controller;

import com.so.wallet.entities.Transaction;
import com.so.wallet.entities.Utilisateur;
import com.so.wallet.service.TransactionService;
import com.so.wallet.util.PdfExporter;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions(@AuthenticationPrincipal Utilisateur utilisateur) {
        return transactionService.getTransactionsByUtilisateur(utilisateur);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction,
                                         @AuthenticationPrincipal Utilisateur utilisateur) {
        transaction.setUtilisateur(utilisateur);
        return transactionService.saveTransaction(transaction);
    }


    @DeleteMapping("del/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
    /*
    @GetMapping("/export/pdf")
    public void exportTransactionsToPDF(HttpServletResponse response) throws IOException, java.io.IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=transactions.pdf");

        List<String> lignes = transactionService.getTransactionsByUtilisateur().stream()
                .map(t -> "Date: " + t.getDate() + ", Montant: " + t.getMontant() + ", Catégorie: " + t.getCategorie().getNom())
                .toList();

        PdfExporter.exportTransactions(response, lignes);
    }*/
    @PutMapping("put/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id,
                                                         @RequestBody Transaction updatedTransaction,
                                                         @AuthenticationPrincipal Utilisateur utilisateur) {
        Optional<Transaction> existingTransaction = transactionService.getTransactionById(id);
    
        if (existingTransaction.isPresent()) {
            Transaction transaction = existingTransaction.get();
    
            transaction.setMontant(updatedTransaction.getMontant());
            transaction.setDate(updatedTransaction.getDate());
            transaction.setDescription(updatedTransaction.getDescription());
            //transaction.setCategorie(updatedTransaction.getCategorie());
            //transaction.setBudget(updatedTransaction.getBudget());
    
            // Important : toujours garder l'utilisateur d'origine
            transaction.setUtilisateur(utilisateur);
    
            Transaction saved = transactionService.saveTransaction(transaction);
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
