// Montelo gia ton xrhsth ths efarmoghs
export interface ApplicationUser {
  userId: number;
  username: string;
  email: string;
  role: 'USER' | 'ADMIN';
}
