package com.budgetmanager.backend.service;

import com.budgetmanager.backend.model.budget;
import com.budgetmanager.backend.repository.budgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class budgetService {

    private final budgetRepository budgetRepository;

    @Autowired
    public budgetService(budgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    // Create a new budget (unchanged, as you confirmed it works)
    public budget createBudget(budget budget) {
        budget.setBalance(budget.getBudgetAmount()); // Set initial balance
        budget.setExpenses(0.0); // Set initial expenses
        return budgetRepository.save(budget);
    }

    // Get budget by ID
    public Optional<budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }
public List<budget> getAllBudgets() {
        return budgetRepository.findAll();
}
    // Update a budget
    public budget updateBudget(Long id, budget budget) {
        Optional<budget> existingBudget = budgetRepository.findById(id);
        if (existingBudget.isPresent()) {
            budget.setId(id); // Ensure the right ID
            return budgetRepository.save(budget);
        } else {
            return null; // Return null if budget doesn't exist
        }
    }

    // Delete a budget
    public boolean deleteBudget(Long id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return true; // Success
        }
        return false; // Budget not found
    }

}