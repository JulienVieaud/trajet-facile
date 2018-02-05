package com.poe.trajetfacile.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poe.trajetfacile.domain.Ride;

@Repository
public interface RideRepository extends CrudRepository<Ride, Long> {

}
