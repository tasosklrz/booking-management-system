package com.bookingmanagement.service;

import com.bookingmanagement.dto.RegisterRequest;
import com.bookingmanagement.exception.ResourceNotFoundException;
import com.bookingmanagement.model.ApplicationUser;
import com.bookingmanagement.model.UserRole;
import com.bookingmanagement.repository.ApplicationUserRepository;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationUserService(
            ApplicationUserRepository applicationUserRepository,
            PasswordEncoder passwordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fortwsh xrhsth gia ton mixanismo asfaleias
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User was not found"));

        return new User(
                applicationUser.getUsername(),
                applicationUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + applicationUser.getRole().name())));
    }

    public ApplicationUser registerUser(RegisterRequest registerRequest) {
        // Elegxos gia monadiko onoma xrhsth kai email prin thn apothhkeysh
        if (applicationUserRepository.existsByUsername(registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (applicationUserRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(registerRequest.getUsername());
        applicationUser.setEmail(registerRequest.getEmail());
        applicationUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        applicationUser.setRole(UserRole.USER);

        return applicationUserRepository.save(applicationUser);
    }

    public ApplicationUser getUserByUsername(String username) {
        // Anazhthsh xrhsth me vash to username
        return applicationUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User was not found"));
    }
}
