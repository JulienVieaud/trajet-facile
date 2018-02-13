package com.poe.trajetfacile.business.service;

import com.poe.trajetfacile.domain.Booking;
import com.poe.trajetfacile.domain.Ride;
import com.poe.trajetfacile.domain.User;
import com.poe.trajetfacile.exception.RideIsFullBusinessException;
import com.poe.trajetfacile.repository.RideRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTests {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private RideService rideService;

    @Autowired
    private RideRepository rideRepository;

    @Test
    public void canBookARide() throws RideIsFullBusinessException {

        short seat = 1;

        assertThat(rideRepository.count()).isEqualTo(0);

        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        userService.signup(user);

        Ride ride = rideService.offerARide(new Date(), "Angers", "Nantes", 3d, seat, user.getId());

        Booking booking = bookingService.bookARide(user.getId(), ride.getId());

        assertThat(booking.getRide().getSeats()).isEqualTo((short) (seat - 1));
        assertThat(rideRepository.findOne(ride.getId()).getSeats()).isEqualTo(seat - 1);

    }

    @Test
    public void noSeatLeft() {

    }

}
