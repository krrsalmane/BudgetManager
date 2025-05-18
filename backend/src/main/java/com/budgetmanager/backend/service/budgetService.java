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


    public budget createBudget(budget budget) {
        budget.setBalance(budget.getBudgetAmount());
        budget.setExpenses(0.0);
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
            budget.setId(id);
            return budgetRepository.save(budget);
        } else {
            return null;

        }
    }

    // Delete a budget
    public boolean deleteBudget(Long id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return true;
        }
        return false;

    }

}