package com.budgetmanager.backend.service;

import com.budgetmanager.backend.model.budget;
import com.budgetmanager.backend.repository.budgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class budgetService {

    private final budgetRepository budgetRepository;

    @Autowired
    public budgetService(budgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    // Create a new budget
    public budget createBudget(budget newBudget) {
        newBudget.setBalance(newBudget.getBudgetAmount());
        newBudget.setExpenses(0.0);
        return budgetRepository.save(newBudget);
    }


}