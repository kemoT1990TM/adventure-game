package com.tkjavadev.adventuregame.config;

import com.tkjavadev.adventuregame.core.LocationId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/game.properties")
//@ComponentScan(basePackages = "com.tkjavadev.adventuregame")
public class GameConfig {

    // == fields ==
    @Value("${game.locationId:1}")
    private Long locationId;

    //== bean methods ==
    @Bean
    @LocationId
    public Long locationId(){
        return locationId;
    }
}
