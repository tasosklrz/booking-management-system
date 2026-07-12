package com.bookingmanagement.service;

import com.bookingmanagement.exception.ResourceNotFoundException;
import com.bookingmanagement.model.BookableService;
import com.bookingmanagement.repository.BookableServiceRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookableServiceService {

    private final BookableServiceRepository bookableServiceRepository;

    public BookableServiceService(BookableServiceRepository bookableServiceRepository) {
        this.bookableServiceRepository = bookableServiceRepository;
    }

    public List<BookableService> getAllBookableServices() {
        return bookableServiceRepository.findAll();
    }

    public BookableService getBookableServiceById(Long serviceId) {
        // Elegxos an yparxei h yphresia pou zhththhke
        return bookableServiceRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Bookable service was not found"));
    }

    public BookableService createBookableService(BookableService bookableService) {
        return bookableServiceRepository.save(bookableService);
    }

    public BookableService updateBookableService(Long serviceId, BookableService serviceDetails) {
        // Enhmerwsh twn stoixeiwn ths yphresias
        BookableService existingService = getBookableServiceById(serviceId);
        existingService.setServiceName(serviceDetails.getServiceName());
        existingService.setDescription(serviceDetails.getDescription());
        existingService.setDurationMinutes(serviceDetails.getDurationMinutes());
        existingService.setPrice(serviceDetails.getPrice());
        return bookableServiceRepository.save(existingService);
    }

    public void deleteBookableService(Long serviceId) {
        // Diagrafh yphresias afou epivevaiwthei oti yparxei
        BookableService existingService = getBookableServiceById(serviceId);
        bookableServiceRepository.delete(existingService);
    }
}
