import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  username = '';
  email = '';
  password = '';
  errorMessage = '';
  successMessage = '';
  isLoading = false;

  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  submitRegister(): void {
    // Ektelesh eggrafhs kai epistrofh sth selida syndeshs
    this.errorMessage = '';
    this.successMessage = '';
    this.isLoading = true;

    this.authService.register(this.username, this.email, this.password).subscribe({
      next: () => {
        this.isLoading = false;
        this.successMessage = 'Ο λογαριασμός δημιουργήθηκε με επιτυχία.';
        setTimeout(() => void this.router.navigate(['/login']), 800);
      },
      error: () => {
        this.isLoading = false;
        this.errorMessage = 'Η εγγραφή απέτυχε. Δοκιμάστε άλλο username ή email.';
      },
    });
  }
}
