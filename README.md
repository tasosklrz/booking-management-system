# Booking Management System

Τελική εργασία για το Coding Factory 9. Η εφαρμογή είναι ένα σύστημα διαχείρισης κρατήσεων με frontend, backend, βάση δεδομένων, REST API, Swagger τεκμηρίωση και μηχανισμό authentication/authorization.

## Τεχνολογίες

- Backend: Java 8, Spring Boot 2.7.18
- Frontend: Angular 20
- Βάση δεδομένων: MySQL 8
- API documentation: Swagger / Springdoc OpenAPI
- Security: Spring Security, HTTP Basic authentication, BCrypt password encoding
- Ρόλοι χρηστών: `USER`, `ADMIN`

Η εφαρμογή καλύπτει τα εξής:

- Domain model: `ApplicationUser`, `Customer`, `BookableService`, `Booking`
- Database: MySQL με tables που δημιουργούνται αυτόματα από JPA/Hibernate
- Backend layers: repositories, services και controllers
- REST API: Spring Boot REST controllers
- Frontend: Angular application
- Authentication / Authorization: Spring Security στο backend και login/register flow στο Angular frontend
- Swagger: διαθέσιμο στο `/swagger-ui.html`
- README: το παρόν αρχείο περιγράφει build και deploy/run βήματα

## Βασικές Λειτουργίες

- Εγγραφή χρήστη
- Σύνδεση χρήστη
- Role based authorization με ρόλους `USER` και `ADMIN`
- Διαχείριση πελατών
- Διαχείριση υπηρεσιών που μπορούν να κρατηθούν
- Δημιουργία κράτησης
- Ενημέρωση κατάστασης κράτησης
- Διαγραφή κράτησης
- Προβολή API μέσω Swagger

## Demo Users

Το backend δημιουργεί αυτόματα τους παρακάτω χρήστες κατά την εκκίνηση, αν δεν υπάρχουν ήδη στη βάση:

- Admin: `admin` / `admin123`
- User: `user` / `user123`

## Ρύθμιση Βάσης Δεδομένων

Η εφαρμογή χρησιμοποιεί MySQL βάση με όνομα:

```sql
booking_management
```

Αν η βάση δεν υπάρχει, μπορεί να δημιουργηθεί από το MySQL Workbench με:

```sql
CREATE DATABASE booking_management;
```

Οι ρυθμίσεις σύνδεσης βρίσκονται στο:

```text
backend/src/main/resources/application.properties
```

Οι τρέχουσες ρυθμίσεις είναι:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/booking_management?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
```

Τα tables δημιουργούνται αυτόματα από το Spring Boot με JPA/Hibernate, επειδή υπάρχει η ρύθμιση:

```properties
spring.jpa.hibernate.ddl-auto=update
```

## Εναλλακτική Εκκίνηση Βάσης Με Docker

Αν υπάρχει Docker εγκατεστημένο, η MySQL μπορεί να ξεκινήσει από το root folder του project με:

```bash
docker compose up -d
```

Το `docker-compose.yml` δημιουργεί MySQL container με:

- Database: `booking_management`
- Username: `root`
- Password: `root`
- Port: `3306`

## Εκτέλεση Backend

Από φάκελο:

```text
backend
```

τρέχουμε:

```bash
mvn spring-boot:run
```

Το backend ανοίγει στο:

```text
http://localhost:8080
```

Το Swagger ανοίγει στο:

```text
http://localhost:8080/swagger-ui.html
```

## Εκτέλεση Frontend

Από φάκελο:

```text
frontend
```

τρέχουμε:

```bash
npm install
npm start
```

Το frontend ανοίγει στο:

```text
http://localhost:4200
```

## Build Backend

Από τον φάκελο `backend`:

```bash
mvn clean test
```

Με αυτή την εντολή γίνεται καθαρό build και compile check του Spring Boot project.

## Build Frontend

Από τον φάκελο `frontend`:

```bash
npm run build
```

Το τελικό Angular build δημιουργείται στον φάκελο:

```text
frontend/dist
```

## Τοπικό Deploy / Run

Για τοπική εκτέλεση της εφαρμογής:

1. Ξεκινήστε τη MySQL και βεβαιωθείτε ότι υπάρχει η βάση `booking_management`.
2. Τρέξτε το backend με `mvn spring-boot:run`.
3. Τρέξτε το frontend με `npm start`.
4. Ανοίξτε το `http://localhost:4200`.
5. Συνδεθείτε με `admin/admin123` ή `user/user123`.

## REST API

Authentication:

- `POST /api/auth/register`
- `GET /api/auth/profile`

Customers:

- `GET /api/customers`
- `GET /api/customers/{customerId}`
- `POST /api/customers`
- `PUT /api/customers/{customerId}`
- `DELETE /api/customers/{customerId}`

Services:

- `GET /api/services`
- `GET /api/services/{serviceId}`
- `POST /api/services`
- `PUT /api/services/{serviceId}`
- `DELETE /api/services/{serviceId}`

Bookings:

- `GET /api/bookings`
- `GET /api/bookings/{bookingId}`
- `GET /api/bookings/customer/{customerId}`
- `POST /api/bookings`
- `PATCH /api/bookings/{bookingId}/status`
- `DELETE /api/bookings/{bookingId}`
