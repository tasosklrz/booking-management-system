import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookableService } from '../models/bookable-service.model';
import { Booking } from '../models/booking.model';
import { Customer } from '../models/customer.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class BookingApiService {
  private readonly httpClient = inject(HttpClient);
  private readonly authService = inject(AuthService);
  private readonly apiUrl = 'http://localhost:8080/api';

  getServices(): Observable<BookableService[]> {
    // Anagnwsh yphresiwn apo ton server
    return this.httpClient.get<BookableService[]>(`${this.apiUrl}/services`, {
      headers: this.authService.getAuthorizationHeaders(),
    });
  }

  createService(serviceForm: Omit<BookableService, 'serviceId'>): Observable<BookableService> {
    return this.httpClient.post<BookableService>(`${this.apiUrl}/services`, serviceForm, {
      headers: this.authService.getAuthorizationHeaders(),
    });
  }

  deleteService(serviceId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiUrl}/services/${serviceId}`, {
      headers: this.authService.getAuthorizationHeaders(),
    });
  }

  getCustomers(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(`${this.apiUrl}/customers`, {
      headers: this.authService.getAuthorizationHeaders(),
    });
  }

  createCustomer(customerForm: Omit<Customer, 'customerId'>): Observable<Customer> {
    // Kataxwrhsh neou pelath ston server
    return this.httpClient.post<Customer>(`${this.apiUrl}/customers`, customerForm, {
      headers: this.authService.getAuthorizationHeaders(),
    });
  }

  deleteCustomer(customerId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiUrl}/customers/${customerId}`, {
      headers: this.authService.getAuthorizationHeaders(),
    });
  }

  getBookings(): Observable<Booking[]> {
    return this.httpClient.get<Booking[]>(`${this.apiUrl}/bookings`, {
      headers: this.authService.getAuthorizationHeaders(),
    });
  }

  createBooking(bookingForm: {
    customerId: number;
    serviceId: number;
    bookingDateTime: string;
  }): Observable<Booking> {
    // Dhmiourgia neas krathshs ston server
    return this.httpClient.post<Booking>(`${this.apiUrl}/bookings`, bookingForm, {
      headers: this.authService.getAuthorizationHeaders(),
    });
  }

  updateBookingStatus(
    bookingId: number,
    bookingStatus: 'PENDING' | 'CONFIRMED' | 'CANCELLED',
  ): Observable<Booking> {
    // Enhmerwsh katastashs krathshs
    return this.httpClient.patch<Booking>(
      `${this.apiUrl}/bookings/${bookingId}/status`,
      { bookingStatus },
      {
        headers: this.authService.getAuthorizationHeaders(),
      },
    );
  }

  deleteBooking(bookingId: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.apiUrl}/bookings/${bookingId}`, {
      headers: this.authService.getAuthorizationHeaders(),
    });
  }
}
