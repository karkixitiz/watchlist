package com.openclassrooms.watchlist.config;

import com.openclassrooms.watchlist.repository.WatchlistRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("app-config.xml") // method 2
public class AppConfig {

}
