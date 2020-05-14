package com.openclassrooms.watchlist.service;

import com.openclassrooms.watchlist.controller.WatchlistController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class MovieRatingService {

    String apiUrl = "http://www.omdbapi.com/?i=tt3896198&apikey=a8ed4a8d&t=";
    private final Logger logger= LoggerFactory.getLogger(MovieRatingService.class);
    public String getMovieRating(String title) {

        try {
            RestTemplate template = new RestTemplate();

            logger.info("OMDb API called with URL: {}",apiUrl +title);
            ResponseEntity<ObjectNode> response =
                    template.getForEntity(apiUrl + title, ObjectNode.class); // this getForEntity() method request for an url and extract into local variable

            ObjectNode jsonObject = response.getBody();

            logger.debug("OMDb API response:{}",jsonObject);
            return jsonObject.path("imdbRating").asText();
        } catch (Exception e) {
            logger.warn("Something went wront while calling OMDb API" + e.getMessage());
            return null;
        }
    }
}
