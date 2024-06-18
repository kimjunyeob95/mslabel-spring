package com.mslabel.mslabel.config;

import com.mslabel.mslabel.repository.UserRepository;
import com.mslabel.mslabel.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
