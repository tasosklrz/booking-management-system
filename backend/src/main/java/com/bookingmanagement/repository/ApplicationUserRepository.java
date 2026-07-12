package com.bookingmanagement.repository;

import com.bookingmanagement.model.ApplicationUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Πρόσβαση στα δεδομένα των χρηστών
 */
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
