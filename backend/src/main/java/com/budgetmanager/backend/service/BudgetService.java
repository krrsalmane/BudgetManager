package com.budgetmanager.backend.service;

import com.budgetmanager.backend.model.*;
import com.budgetmanager.backend.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

//     Create a new budget
    public Budget createBudget(Budget newBudget) {
        return budgetRepository.save(newBudget);
    }


}