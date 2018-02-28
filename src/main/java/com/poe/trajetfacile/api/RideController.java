
package com.poe.trajetfacile.api;

import com.poe.trajetfacile.domain.Ride;
import com.poe.trajetfacile.repository.RideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ride")
public class RideController {

	private static final Logger LOG = LoggerFactory.getLogger(RideController.class);

    @Autowired
    private RideRepository rideRepository;

    @GetMapping("{idRide}")
    public Ride find(@PathVariable("idRide") Long rideId) {
        return rideRepository.findOne(rideId);
    }

    @GetMapping
    public Iterable<Ride> findAll() {
        return rideRepository.findAll();
    }

    @PostMapping
    public void save(@RequestBody Ride ride) {
		LOG.info("save | ride: " + ride);
        rideRepository.save(ride);
    }

    @GetMapping("{text}")
    public List<Ride> search(@PathVariable("text") String text) {
        return rideRepository.findAllByToCityLikeIgnoreCaseOrFromCityLikeIgnoreCase(text, text);
    }

}