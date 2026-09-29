package org.example.ridehailing.ridehailingmanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.example.ridehailing.ridehailingmanagement.entities.Booking;
import org.example.ridehailing.ridehailingmanagement.services.BookingSevice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingSevice bookingSevice;

    // Xem tất cả
    @GetMapping
    public List<Booking> getAll() {
        return bookingSevice.getAll();
    }

    // Lấy theo id
    @GetMapping("{id}")
    public Booking getById(@PathVariable Long id) {
        return bookingSevice.getById(id);
    }

    // Thêm mới
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingSevice.createBooking(booking);
    }

    // Cập nhật
    @PutMapping("{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingSevice.updateBooking(id, booking);
    }

    // Xóa
    @DeleteMapping("{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingSevice.deleteBooking(id);
    }
}