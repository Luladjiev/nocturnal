import { Component } from '@angular/core';
import { AsyncPipe, CommonModule, JsonPipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { RouterModule, RouterOutlet } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [CommonModule, RouterModule],
  imports: [RouterOutlet, AsyncPipe, JsonPipe],
})
export class AppComponent {
  constructor(private http: HttpClient) {}

  logout(event: Event) {
    event.preventDefault();

    this.http.post('/api/logout', null).subscribe({
      next: () => location.reload(),
    });
  }
}
