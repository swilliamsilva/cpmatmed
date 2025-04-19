import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']

})
export class AppComponent {
  currentYear: number = new Date().getFullYear();
  title = 'cpmatmed-frontend';
}
