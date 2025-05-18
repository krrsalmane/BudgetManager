import { Component, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface Budget {
  id?: number;
  budgetName: string;
  budgetAmount: number;
  balance: number;
  expenses: number;
}

@Component({
  selector: 'app-budget',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {
  budgets: Budget[] = []; // Ensure this is public and typed
  newBudget: Budget = { budgetName: '', budgetAmount: 0, balance: 0, expenses: 0 }; // Ensure this is public
  isEditing: boolean = false; // Ensure this is public
  errorMessage: string | null = null; // Ensure this is public

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getBudgets();
  }

  getBudgets() {
    this.http.get<Budget[]>('http://localhost:8283/api/budgets').subscribe({
      next: (data) => {
        this.budgets = data;
        this.errorMessage = null;
      },
      error: () => {
        this.errorMessage = 'Failed to load budgets. Please try again.';
      }
    });
  }

  addBudget() {
    if (!this.newBudget.budgetName || this.newBudget.budgetAmount <= 0) {
      this.errorMessage = 'Please enter a valid budget name and amount.';
      return;
    }
    if (this.isEditing) {
      this.http.put<Budget>(`http://localhost:8283/api/budgets/${this.newBudget.id}`, this.newBudget).subscribe({
        next: () => {
          this.getBudgets();
          this.resetForm();
        },
        error: () => {
          this.errorMessage = 'Failed to update budget. Please try again.';
        }
      });
    } else {
      this.http.post<Budget>('http://localhost:8283/api/budgets', this.newBudget).subscribe({
        next: () => {
          this.getBudgets();
          this.resetForm();
        },
        error: () => {
          this.errorMessage = 'Failed to add budget. Please try again.';
        }
      });
    }
  }

  editBudget(budget: Budget) {
    this.newBudget = { ...budget };
    this.isEditing = true;
  }

  deleteBudget(id: number | undefined) {
    if (id) {
      this.http.delete(`http://localhost:8283/api/budgets/${id}`).subscribe({
        next: () => {
          this.getBudgets();
        },
        error: () => {
          this.errorMessage = 'Failed to delete budget. Please try again.';
        }
      });
    }
  }

  resetForm() {
    this.newBudget = { budgetName: '', budgetAmount: 0, balance: 0, expenses: 0 };
    this.isEditing = false;
    this.errorMessage = null;
  }
}
