package com.poe.trajetfacile.service;

import com.poe.trajetfacile.domain.Ride;
import com.poe.trajetfacile.repository.RideRepository;
import com.poe.trajetfacile.repository.UserRepository;
import com.poe.trajetfacile.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business Code about user management.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    public void signup(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void addRide(Long userId, Long rideId) {
        User user = userRepository.findOne(userId);
        Ride ride = rideRepository.findOne(rideId);
        user.getProposedRides().add(ride);
        ride.setUserWhoProposed(user);
        userRepository.save(user);
    }
}
