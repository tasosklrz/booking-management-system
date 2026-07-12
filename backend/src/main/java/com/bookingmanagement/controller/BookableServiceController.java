package com.bookingmanagement.controller;

import com.bookingmanagement.model.BookableService;
import com.bookingmanagement.service.BookableServiceService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/services")
public class BookableServiceController {

    private final BookableServiceService bookableServiceService;

    public BookableServiceController(BookableServiceService bookableServiceService) {
        this.bookableServiceService = bookableServiceService;
    }

    @GetMapping
    public List<BookableService> getAllBookableServices() {
        // Ανάγνωση όλων των διαθέσιμων υπηρεσιων
        return bookableServiceService.getAllBookableServices();
    }

    @GetMapping("/{serviceId}")
    public BookableService getBookableServiceById(@PathVariable Long serviceId) {
        return bookableServiceService.getBookableServiceById(serviceId);
    }

    @PostMapping
    public ResponseEntity<BookableService> createBookableService(@Valid @RequestBody BookableService bookableService) {
        // Καταχώρηση νέας υπηρεσίας
        BookableService savedService = bookableServiceService.createBookableService(bookableService);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedService);
    }

    @PutMapping("/{serviceId}")
    public BookableService updateBookableService(
            @PathVariable Long serviceId,
            @Valid @RequestBody BookableService serviceDetails) {
        return bookableServiceService.updateBookableService(serviceId, serviceDetails);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteBookableService(@PathVariable Long serviceId) {
        // Διαγραφή υπηρεσίας με βάση το ID.
        bookableServiceService.deleteBookableService(serviceId);
        return ResponseEntity.noContent().build();
    }
}
