package com.so.wallet.controller;

import com.so.wallet.entities.Budget;
import com.so.wallet.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        Optional<Budget> budget = budgetService.getBudgetById(id);
        return budget.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.saveBudget(budget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/ajuster")
    public ResponseEntity<Budget> ajusterBudget(@PathVariable Long id, @RequestParam Double montant) {
        Budget budgetAjuste = budgetService.ajusterBudget(id, montant);
        return ResponseEntity.ok(budgetAjuste);
    }
    @GetMapping("/{id}/solde")
    public ResponseEntity<Double> getSolde(@PathVariable Long id) {
        double solde = budgetService.calculerSolde(id);
        return ResponseEntity.ok(solde);
    }

}
