package com.openclassrooms.watchlist.config;

import com.openclassrooms.watchlist.repository.WatchlistRepository;
import com.openclassrooms.watchlist.service.MovieRatingService;
import com.openclassrooms.watchlist.service.WatchlistService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@ImportResource("app-config.xml") // method 2
public class AppConfig {

    @Bean
    public WatchlistRepository watchlistRepository(){
        return new WatchlistRepository();
    }

    @Bean
    public MovieRatingService movieRatingService(){
        return new MovieRatingService();
    }

    @Bean
    public WatchlistService watchlistService(){ //watchlistservice get two classes as constructor parameters
        return new WatchlistService(watchlistRepository(),movieRatingService());
    }
}
