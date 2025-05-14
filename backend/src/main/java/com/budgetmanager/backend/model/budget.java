package com.budgetmanager.backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;



public class budget {

    private long id;
    private double budgetAmount;
    private double balance;
    private double expenses;

    public budget(long id, double budgetAmount, double balance, double expenses) {
        this.id = id;
        this.budgetAmount = budgetAmount;
        this.balance = balance;
        this.expenses = expenses;
    }

    public budget() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }
}

