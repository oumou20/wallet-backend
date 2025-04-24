package com.so.wallet.controller;

import com.so.wallet.entities.Transaction;
import com.so.wallet.service.TransactionService;
import com.so.wallet.util.PdfExporter;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/export/pdf")
    public void exportTransactionsToPDF(HttpServletResponse response) throws IOException, java.io.IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=transactions.pdf");

        List<String> lignes = transactionService.getAllTransactions().stream()
                .map(t -> "Date: " + t.getDate() + ", Montant: " + t.getMontant() + ", Catégorie: " + t.getCategorie().getNom())
                .toList();

        PdfExporter.exportTransactions(response, lignes);
    }

}
