package com.poe.trajetfacile.api;

import com.poe.trajetfacile.domain.Booking;
import com.poe.trajetfacile.domain.Ride;
import com.poe.trajetfacile.domain.User;
import com.poe.trajetfacile.repository.BookingRepository;
import com.poe.trajetfacile.repository.RideRepository;
import com.poe.trajetfacile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    RideRepository rideRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookingRepository bookingRepository;

    @GetMapping
    public Iterable<Booking> list() {
        return bookingRepository.findAll();
    }

    @PostMapping("{userId}/{rideId}")
    public void save(@PathVariable("userId") Long userId, @PathVariable("rideId") Long rideId) {
        User user = userRepository.findOne(userId);
        Ride ride = rideRepository.findOne(rideId);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRide(ride);
        bookingRepository.save(booking);

    }

}
