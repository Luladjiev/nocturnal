import { Component } from '@angular/core';
import { AsyncPipe, CommonModule, JsonPipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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
  user$ = this.http.get('/api/user');

  constructor(private http: HttpClient) {}
  login() {
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/x-www-form-urlencoded'
    );

    const body = new HttpParams()
      .set('username', 'admin')
      .set('password', '123');

    console.log(body.toString());
    this.http.post('/api/login', body.toString(), { headers }).subscribe();
  }

  logout() {
    this.http.post('/api/logout', null).subscribe();
  }
}
