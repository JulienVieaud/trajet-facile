package com.poe.trajetfacile.controller;

import com.poe.trajetfacile.domain.Booking;
import com.poe.trajetfacile.domain.Ride;
import com.poe.trajetfacile.domain.User;
import com.poe.trajetfacile.repository.RideRepository;
import com.poe.trajetfacile.repository.UserRepository;
import com.poe.trajetfacile.service.BookingService;
import com.poe.trajetfacile.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class BookingController {

    @Autowired
    private RideService rideService;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BookingService bookingService;

    @GetMapping
    public String bookARide(@RequestParam(name = "ride") String rideId, @RequestParam(name = "user") String userId, Model model) {
        if (rideId != null && !rideId.isEmpty()) {
            Booking booking = bookingService.bookARide(Long.valueOf(userId), Long.valueOf(rideId));
            model.addAttribute("book", booking);
        }
        return "ride/booked";
    }
}
