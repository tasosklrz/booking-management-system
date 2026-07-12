package com.bookingmanagement;

import com.bookingmanagement.model.ApplicationUser;
import com.bookingmanagement.model.BookableService;
import com.bookingmanagement.model.UserRole;
import com.bookingmanagement.repository.ApplicationUserRepository;
import com.bookingmanagement.repository.BookableServiceRepository;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ApplicationUserRepository applicationUserRepository;
    private final BookableServiceRepository bookableServiceRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            ApplicationUserRepository applicationUserRepository,
            BookableServiceRepository bookableServiceRepository,
            PasswordEncoder passwordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bookableServiceRepository = bookableServiceRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Arxikopoihsh vasikwn dedomenwn kata thn ekkinhsh ths efarmoghs
        createDefaultUsers();
        createDefaultServices();
    }

    private void createDefaultUsers() {
        // Dhmiourgia proepilegmenwn xrhstwn gia dokimh tou systhmatos
        if (!applicationUserRepository.existsByUsername("admin")) {
            ApplicationUser adminUser = new ApplicationUser();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@booking.gr");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setRole(UserRole.ADMIN);
            applicationUserRepository.save(adminUser);
        }

        if (!applicationUserRepository.existsByUsername("user")) {
            ApplicationUser simpleUser = new ApplicationUser();
            simpleUser.setUsername("user");
            simpleUser.setEmail("user@booking.gr");
            simpleUser.setPassword(passwordEncoder.encode("user123"));
            simpleUser.setRole(UserRole.USER);
            applicationUserRepository.save(simpleUser);
        }
    }

    private void createDefaultServices() {
        // Apofygh diplwn kataxwrhsewn otan yparxoun hdh yphresies
        if (bookableServiceRepository.count() > 0) {
            return;
        }

        bookableServiceRepository.save(createBookableService(
                "Δωμάτιο Ξενοδοχείου",
                "Κράτηση απλού δωματίου για μία διανυκτέρευση.",
                1440,
                new BigDecimal("75.00")));

        bookableServiceRepository.save(createBookableService(
                "Τραπέζι Εστιατορίου",
                "Κράτηση τραπεζιού για δείπνο.",
                120,
                new BigDecimal("0.00")));

        bookableServiceRepository.save(createBookableService(
                "Αίθουσα Συνεδριάσεων",
                "Κράτηση αίθουσας για επαγγελματική συνάντηση.",
                180,
                new BigDecimal("120.00")));
    }

    private BookableService createBookableService(
            String serviceName,
            String description,
            Integer durationMinutes,
            BigDecimal price) {
        // Sygkentrwsh ths dhmiourgias yphresias se ena shmeio
        BookableService bookableService = new BookableService();
        bookableService.setServiceName(serviceName);
        bookableService.setDescription(description);
        bookableService.setDurationMinutes(durationMinutes);
        bookableService.setPrice(price);
        return bookableService;
    }
}
