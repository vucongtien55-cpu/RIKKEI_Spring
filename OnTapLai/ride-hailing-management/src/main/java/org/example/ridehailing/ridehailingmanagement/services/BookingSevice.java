package org.example.ridehailing.ridehailingmanagement.services;

import lombok.RequiredArgsConstructor;
import org.example.ridehailing.ridehailingmanagement.entities.Booking;
import org.example.ridehailing.ridehailingmanagement.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingSevice {
    private final BookingRepository bookingRepository;

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Booking getById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy booking theo ID: " + id));
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking booking) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy booking có id: " + id));

        existingBooking.setCustomer(booking.getCustomer());
        existingBooking.setDriver(booking.getDriver());
        existingBooking.setVehicle(booking.getVehicle());
        existingBooking.setPickupLocation(booking.getPickupLocation());
        existingBooking.setDestination(booking.getDestination());
        existingBooking.setPrice(booking.getPrice());
        existingBooking.setStatus(booking.getStatus());

        return bookingRepository.save(existingBooking);
    }

    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy booking có id: " + id));
        bookingRepository.delete(booking);
    }
}