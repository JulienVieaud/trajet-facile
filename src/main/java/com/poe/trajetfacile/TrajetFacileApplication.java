package com.poe.trajetfacile;

import com.poe.trajetfacile.domain.User;
import com.poe.trajetfacile.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrajetFacileApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(TrajetFacileApplication.class, args);
    }

    @Bean
    public InitializingBean init() {
        return () -> {
            System.out.println("init");
            User user = new User();
            user.setLogin("jean");
            user.setPassword("pass");
            userRepository.save(user);
            user = new User();
            user.setLogin("jean");
            user.setPassword("pass");
            userRepository.save(user);
        };
    }
}
