import { BookableService } from './bookable-service.model';
import { Customer } from './customer.model';

// Montelo gia krathsh pou emfanizetai ston pinaka elegxou
export interface Booking {
  bookingId: number;
  customer: Customer;
  bookableService: BookableService;
  bookingDateTime: string;
  bookingStatus: 'PENDING' | 'CONFIRMED' | 'CANCELLED';
}
