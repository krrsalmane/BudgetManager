package com.budgetmanager.backend.controller;

import com.budgetmanager.backend.model.budget;
import com.budgetmanager.backend.service.budgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
public class budgetController {

    private final budgetService budgetService;

    @Autowired
    public budgetController(budgetService budgetService) {
        this.budgetService = budgetService;
    }

    // Create a new budget (unchanged, as you confirmed it works)
    @PostMapping
    public budget createBudget(@RequestBody budget budget) {
        return budgetService.createBudget(budget);
    }

    // Get budget by ID
    @GetMapping("/{id}")
    public ResponseEntity<budget> getBudgetById(@PathVariable Long id) {
        Optional<budget> budget = budgetService.getBudgetById(id);
        if (budget.isPresent()) {
            return new ResponseEntity<>(budget.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public List<budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    // Update a budget
    @PutMapping("/{id}")
    public ResponseEntity<budget> updateBudget(@PathVariable Long id, @RequestBody budget budget) {
        budget updatedBudget = budgetService.updateBudget(id, budget);
        if (updatedBudget != null) {
            return new ResponseEntity<>(updatedBudget, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a budget

}