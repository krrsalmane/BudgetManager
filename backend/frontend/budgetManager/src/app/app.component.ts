import { Component } from '@angular/core';
import { BudgetComponent } from './budget/budget.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [BudgetComponent],
  template: '<app-budget></app-budget>',
  styleUrls: ['./app.component.css']
})
export class AppComponent {}
