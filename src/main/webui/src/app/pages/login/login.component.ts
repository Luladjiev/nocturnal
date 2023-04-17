import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  loginForm = new FormGroup({
    username: new FormControl('', { validators: [Validators.required] }),
    password: new FormControl('', { validators: [Validators.required] }),
  });

  error = false;

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    const headers = new HttpHeaders().set(
      'Content-Type',
      'application/x-www-form-urlencoded'
    );

    const username = this.loginForm.value.username || '';
    const password = this.loginForm.value.password || '';

    const body = new HttpParams()
      .set('username', username)
      .set('password', password);

    this.http.post('/api/login', body.toString(), { headers }).subscribe({
      next: () => this.router.navigate(['/']),
      error: () => (this.error = true),
    });
  }
}
