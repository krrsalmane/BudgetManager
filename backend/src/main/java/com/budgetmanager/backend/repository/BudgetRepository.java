package com.budgetmanager.backend.repository;
import com.budgetmanager.backend.model.Budget;
import com.budgetmanager.backend.service.BudgetService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BudgetRepository extends JpaRepository<Budget, Long> {

}