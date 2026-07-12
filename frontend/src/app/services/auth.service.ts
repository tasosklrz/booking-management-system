import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { ApplicationUser } from '../models/application-user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly httpClient = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/auth';
  private readonly credentialsStorageKey = 'bookingManagementCredentials';
  private readonly userStorageKey = 'bookingManagementUser';

  login(username: string, password: string): Observable<ApplicationUser> {
    // Dhmiourgia stoixeiwn syndeshs gia to aitima
    const encodedCredentials = btoa(`${username}:${password}`);
    const httpHeaders = new HttpHeaders({
      Authorization: `Basic ${encodedCredentials}`,
    });

    return this.httpClient
      .get<ApplicationUser>(`${this.apiUrl}/profile`, { headers: httpHeaders })
      .pipe(
        tap((applicationUser) => {
          localStorage.setItem(this.credentialsStorageKey, encodedCredentials);
          localStorage.setItem(this.userStorageKey, JSON.stringify(applicationUser));
        }),
      );
  }

  register(username: string, email: string, password: string): Observable<ApplicationUser> {
    // Apostolh stoixeiwn eggrafhs ston server
    return this.httpClient.post<ApplicationUser>(`${this.apiUrl}/register`, {
      username,
      email,
      password,
    });
  }

  logout(): void {
    // Katharismos topikwn stoixeiwn syndeshs
    localStorage.removeItem(this.credentialsStorageKey);
    localStorage.removeItem(this.userStorageKey);
  }

  isLoggedIn(): boolean {
    return Boolean(this.getEncodedCredentials());
  }

  getCurrentUser(): ApplicationUser | null {
    const storedUser = localStorage.getItem(this.userStorageKey);
    return storedUser ? (JSON.parse(storedUser) as ApplicationUser) : null;
  }

  getAuthorizationHeaders(): HttpHeaders {
    // Prosthikh kefalidas eksousiodothshs sta aithmata tou API
    const encodedCredentials = this.getEncodedCredentials();
    return new HttpHeaders({
      Authorization: encodedCredentials ? `Basic ${encodedCredentials}` : '',
    });
  }

  private getEncodedCredentials(): string | null {
    return localStorage.getItem(this.credentialsStorageKey);
  }
}
