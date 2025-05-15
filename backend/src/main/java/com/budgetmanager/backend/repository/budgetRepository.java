package com.budgetmanager.backend.repository;

import com.budgetmanager.backend.model.budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface budgetRepository extends JpaRepository<budget, Long> {
}