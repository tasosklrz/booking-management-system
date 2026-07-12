package com.bookingmanagement.controller;

import com.bookingmanagement.dto.RegisterRequest;
import com.bookingmanagement.model.ApplicationUser;
import com.bookingmanagement.service.ApplicationUserService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ApplicationUserService applicationUserService;

    public AuthController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        // Καταχώριση νέου χρήστη στο σύστημα
        ApplicationUser applicationUser = applicationUserService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationUser);
    }

    @GetMapping("/profile")
    public ApplicationUser getAuthenticatedUser(Principal principal) {
        // Επιστροφη στοιχείων του συνδεδεμένου χρήστη
        return applicationUserService.getUserByUsername(principal.getName());
    }
}
