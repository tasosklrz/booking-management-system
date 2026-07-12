import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ApplicationUser } from '../../models/application-user.model';
import { BookableService } from '../../models/bookable-service.model';
import { Booking } from '../../models/booking.model';
import { Customer } from '../../models/customer.model';
import { AuthService } from '../../services/auth.service';
import { BookingApiService } from '../../services/booking-api.service';

type BookingStatus = 'PENDING' | 'CONFIRMED' | 'CANCELLED';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, FormsModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  currentUser: ApplicationUser | null = null;
  activeSection: 'bookings' | 'customers' | 'services' = 'bookings';
  services: BookableService[] = [];
  customers: Customer[] = [];
  bookings: Booking[] = [];
  message = '';
  errorMessage = '';

  serviceForm = {
    serviceName: '',
    description: '',
    durationMinutes: 60,
    price: 0,
  };

  customerForm = {
    fullName: '',
    email: '',
    phoneNumber: '',
  };

  bookingForm = {
    customerId: 0,
    serviceId: 0,
    bookingDateTime: '',
  };

  private readonly authService = inject(AuthService);
  private readonly bookingApiService = inject(BookingApiService);
  private readonly router = inject(Router);

  ngOnInit(): void {
    // Fortwsh arxikwn dedomenwn otan anoigei o pinakas elegxou
    this.currentUser = this.authService.getCurrentUser();
    this.loadDashboardData();
  }

  get isAdmin(): boolean {
    return this.currentUser?.role === 'ADMIN';
  }

  getBookingStatusLabel(bookingStatus: BookingStatus): string {
    // Metatroph eswterikhs katastashs se keimeno gia ton xrhsth
    const statusLabels: Record<BookingStatus, string> = {
      PENDING: 'Σε αναμονή',
      CONFIRMED: 'Επιβεβαιωμένη',
      CANCELLED: 'Ακυρωμένη',
    };

    return statusLabels[bookingStatus];
  }

  setActiveSection(sectionName: 'bookings' | 'customers' | 'services'): void {
    // Allagh energhs enothtas ston pinaka elegxou
    this.activeSection = sectionName;
    this.clearMessages();
  }

  logout(): void {
    this.authService.logout();
    void this.router.navigate(['/login']);
  }

  loadDashboardData(): void {
    this.loadServices();
    this.loadCustomers();
    this.loadBookings();
  }

  createService(): void {
    // Kataxwrhsh yphresias apo th forma
    this.clearMessages();

    this.bookingApiService.createService(this.serviceForm).subscribe({
      next: () => {
        this.message = 'Η υπηρεσία δημιουργήθηκε με επιτυχία.';
        this.serviceForm = {
          serviceName: '',
          description: '',
          durationMinutes: 60,
          price: 0,
        };
        this.loadServices();
      },
      error: () => {
        this.errorMessage = 'Η υπηρεσία δεν μπόρεσε να δημιουργηθεί.';
      },
    });
  }

  deleteService(serviceId: number): void {
    this.clearMessages();

    this.bookingApiService.deleteService(serviceId).subscribe({
      next: () => {
        this.message = 'Η υπηρεσία διαγράφηκε με επιτυχία.';
        this.loadServices();
      },
      error: () => {
        this.errorMessage = 'Η υπηρεσία δεν μπόρεσε να διαγραφεί.';
      },
    });
  }

  createCustomer(): void {
    // Kataxwrhsh pelath apo th forma
    this.clearMessages();

    this.bookingApiService.createCustomer(this.customerForm).subscribe({
      next: () => {
        this.message = 'Ο πελάτης δημιουργήθηκε με επιτυχία.';
        this.customerForm = {
          fullName: '',
          email: '',
          phoneNumber: '',
        };
        this.loadCustomers();
      },
      error: () => {
        this.errorMessage = 'Ο πελάτης δεν μπόρεσε να δημιουργηθεί.';
      },
    });
  }

  deleteCustomer(customerId: number): void {
    this.clearMessages();

    this.bookingApiService.deleteCustomer(customerId).subscribe({
      next: () => {
        this.message = 'Ο πελάτης διαγράφηκε με επιτυχία.';
        this.loadCustomers();
      },
      error: () => {
        this.errorMessage = 'Ο πελάτης δεν μπόρεσε να διαγραφεί.';
      },
    });
  }

  createBooking(): void {
    // Kataxwrhsh krathshs apo th forma
    this.clearMessages();

    this.bookingApiService.createBooking(this.bookingForm).subscribe({
      next: () => {
        this.message = 'Η κράτηση δημιουργήθηκε με επιτυχία.';
        this.bookingForm = {
          customerId: 0,
          serviceId: 0,
          bookingDateTime: '',
        };
        this.loadBookings();
      },
      error: () => {
        this.errorMessage = 'Η κράτηση δεν μπόρεσε να δημιουργηθεί.';
      },
    });
  }

  updateBookingStatus(bookingId: number, bookingStatus: BookingStatus): void {
    this.clearMessages();

    this.bookingApiService.updateBookingStatus(bookingId, bookingStatus).subscribe({
      next: () => {
        this.message = 'Η κατάσταση της κράτησης ενημερώθηκε με επιτυχία.';
        this.loadBookings();
      },
      error: () => {
        this.errorMessage = 'Η κατάσταση της κράτησης δεν μπόρεσε να ενημερωθεί.';
      },
    });
  }

  deleteBooking(bookingId: number): void {
    this.clearMessages();

    this.bookingApiService.deleteBooking(bookingId).subscribe({
      next: () => {
        this.message = 'Η κράτηση διαγράφηκε με επιτυχία.';
        this.loadBookings();
      },
      error: () => {
        this.errorMessage = 'Η κράτηση δεν μπόρεσε να διαγραφεί.';
      },
    });
  }

  private loadServices(): void {
    this.bookingApiService.getServices().subscribe({
      next: (services) => {
        this.services = services;
      },
      error: () => {
        this.errorMessage = 'Οι υπηρεσίες δεν μπόρεσαν να φορτωθούν.';
      },
    });
  }

  private loadCustomers(): void {
    this.bookingApiService.getCustomers().subscribe({
      next: (customers) => {
        this.customers = customers;
      },
      error: () => {
        this.errorMessage = 'Οι πελάτες δεν μπόρεσαν να φορτωθούν.';
      },
    });
  }

  private loadBookings(): void {
    this.bookingApiService.getBookings().subscribe({
      next: (bookings) => {
        this.bookings = bookings;
      },
      error: () => {
        this.errorMessage = 'Οι κρατήσεις δεν μπόρεσαν να φορτωθούν.';
      },
    });
  }

  private clearMessages(): void {
    this.message = '';
    this.errorMessage = '';
  }
}
