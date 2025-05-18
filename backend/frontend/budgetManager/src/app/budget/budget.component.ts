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
  newBudget: Budget = this.emptyBudget();
  isEditing = false;
  errorMessage: string | null = null;
  private api = 'http://localhost:8283/api/budgets';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadBudgets();
  }

  loadBudgets() {
    this.http.get<Budget[]>(this.api).subscribe({
      next: data => { this.budgets = data; this.errorMessage = null; },
      error: () => this.showError('Failed to load budgets.')
    });
  }

  saveBudget() {
    if (!this.newBudget.budgetName || this.newBudget.budgetAmount <= 0) {
      return this.showError('Enter a valid name and amount.');
    }

    const request = this.isEditing
        ? this.http.put(`${this.api}/${this.newBudget.id}`, this.newBudget)
        : this.http.post(this.api, this.newBudget);

    request.subscribe({
      next: () => { this.loadBudgets(); this.reset(); },
      error: () => this.showError(`Failed to ${this.isEditing ? 'update' : 'add'} budget.`)
    });
  }

  editBudget(budget: Budget) {
    this.newBudget = { ...budget };
    this.isEditing = true;
  }

  deleteBudget(id?: number) {
    if (!id) return;
    this.http.delete(`${this.api}/${id}`).subscribe({
      next: () => this.loadBudgets(),
      error: () => this.showError('Failed to delete budget.')
    });
  }

  reset() {
    this.newBudget = this.emptyBudget();
    this.isEditing = false;
    this.errorMessage = null;
  }

  private emptyBudget(): Budget {
    return { budgetName: '', budgetAmount: 0, balance: 0, expenses: 0 };
  }

  private showError(msg: string) {
    this.errorMessage = msg;
  }
}
