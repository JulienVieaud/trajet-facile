package com.poe.trajetfacile;

import com.poe.trajetfacile.repository.RideRepository;
import com.poe.trajetfacile.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrajetFacileApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrajetFacileApplication.class, args);
    }
}
