package com.budgetmanager.backend.controller;

import com.budgetmanager.backend.model.budget;
import com.budgetmanager.backend.service.budgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
public class budgetController {

    private final budgetService budgetService;

    @Autowired
    public budgetController(budgetService budgetService) {
        this.budgetService = budgetService;
    }

    // Create a new budget
    @PostMapping
    public ResponseEntity<budget> createBudget(@RequestBody budget budget) {
        budget createdBudget = budgetService.createBudget(budget);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }

    // Get budget by ID
    @GetMapping("/{id}")
    public ResponseEntity<budget> getBudgetById(@PathVariable Long id) {
        Optional<budget> budget = budgetService.getBudgetById(id);
        return budget.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}