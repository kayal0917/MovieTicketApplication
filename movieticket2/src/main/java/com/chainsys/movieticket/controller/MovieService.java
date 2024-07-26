package com.chainsys.movieticket.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class MovieService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode searchMoviesByName(String movieName) {
        String searchUrl = "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + movieName;
        return restTemplate.getForObject(searchUrl, JsonNode.class);
    }

    public JsonNode getMovieDetails(int movieId) {
        String detailsUrl = apiUrl + movieId + "?api_key=" + apiKey;
        return restTemplate.getForObject(detailsUrl, JsonNode.class);
    }
}