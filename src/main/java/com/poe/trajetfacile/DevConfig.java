package com.poe.trajetfacile;

import com.poe.trajetfacile.domain.User;
import com.poe.trajetfacile.repository.RideRepository;
import com.poe.trajetfacile.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RideRepository rideRepository;

    @Bean
    public InitializingBean init() {
        return () -> {
            System.out.println("init");
            User user = new User();
            user.setLogin("jean");
            user.setPassword("pass");
            userRepository.save(user);

            user = new User();
            user.setLogin("marc");
            user.setPassword("pass");
            userRepository.save(user);


        };
    }
}
