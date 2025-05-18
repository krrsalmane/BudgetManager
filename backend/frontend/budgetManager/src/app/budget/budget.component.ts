import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

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
  budgets: Budget[] = [];
  newBudget: Budget = this.getEmptyBudget();
  isEditing = false;
  errorMessage: string | null = null;

  private apiUrl = 'http://localhost:8283/api/budgets';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getBudgets();
  }

  getBudgets() {
    this.http.get<Budget[]>(this.apiUrl).subscribe({
      next: (data) => {
        this.budgets = data;
        this.errorMessage = null;
      },
      error: () => this.errorMessage = 'Failed to load budgets. Please try again.'
    });
  }

  saveBudget() {
    if (!this.validateBudget()) return;

    const request = this.isEditing
        ? this.http.put<Budget>(`${this.apiUrl}/${this.newBudget.id}`, this.newBudget)
        : this.http.post<Budget>(this.apiUrl, this.newBudget);

    request.subscribe({
      next: () => {
        this.getBudgets();
        this.resetForm();
      },
      error: () => this.errorMessage = `Failed to ${this.isEditing ? 'update' : 'add'} budget. Please try again.`
    });
  }

  editBudget(budget: Budget) {
    this.newBudget = { ...budget };
    this.isEditing = true;
  }

  deleteBudget(id?: number) {
    if (!id) return;

    this.http.delete(`${this.apiUrl}/${id}`).subscribe({
      next: () => this.getBudgets(),
      error: () => this.errorMessage = 'Failed to delete budget. Please try again.'
    });
  }

  resetForm() {
    this.newBudget = this.getEmptyBudget();
    this.isEditing = false;
    this.errorMessage = null;
  }

  private validateBudget(): boolean {
    if (!this.newBudget.budgetName || this.newBudget.budgetAmount <= 0) {
      this.errorMessage = 'Please enter a valid budget name and amount.';
      return false;
    }
    return true;
  }

  private getEmptyBudget(): Budget {
    return { budgetName: '', budgetAmount: 0, balance: 0, expenses: 0 };
  }
}
