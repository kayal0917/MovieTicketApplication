package com.chainsys.movieticket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chainsys.movieticket.model.Movie;
import com.chainsys.movieticket.model.MovieCard;



@Service
public class MovieService {

    @Value("${omdb.api.key}")
    private String apiKey;
    private final String BASE_URL = "http://www.omdbapi.com/";

    public Movie getMovieDetailsById(String imdbId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?i=%s&apikey=%s", BASE_URL, imdbId, apiKey);
        return restTemplate.getForObject(url, Movie.class);
    }

    public Movie getMovieDetailsByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?t=%s&apikey=%s", BASE_URL, title, apiKey);
        return restTemplate.getForObject(url, Movie.class);
    }

    public Movie getMovieDetailsByTitleAndYear(String title, String year) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?t=%s&y=%s&apikey=%s", BASE_URL, title, year, apiKey);
        return restTemplate.getForObject(url, Movie.class);
    }

    public Movie searchMoviesByTitle(String title) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?s=%s&apikey=%s", BASE_URL, title, apiKey);
        return restTemplate.getForObject(url, Movie.class);
    }
    
    private static final String BASE_URL2 = "https://api.themoviedb.org/3";
    private static final String API_KEY = "fae55f73667d820050fd9369e276852d";

    public List<Map<String, Object>> getMoviesByYear(String year) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/discover/movie?primary_release_year=%s&api_key=%s", BASE_URL2, year, API_KEY);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> movies = (List<Map<String, Object>>) response.get("results");
        return movies;
    }

    public MovieCard getMovieDetailsByTitleAndYear1(String title, String year) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/search/movie?query=%s&year=%s&api_key=%s", BASE_URL2, title, year, API_KEY);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
        if (results.isEmpty()) {
            return null;
        }
        int movieId = (int) results.get(0).get("id");
        return getMovieDetails(movieId);
    }

    public MovieCard getMovieDetails(int movieId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/movie/%d?api_key=%s", BASE_URL2, movieId, API_KEY);
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        // Convert the response map to a MovieCard object
        return convertToMovieCard(response);
    }

    private MovieCard convertToMovieCard(Map<String, Object> movieMap) {
        MovieCard movieCard = new MovieCard();
        movieCard.setTitle((String) movieMap.get("title"));
        movieCard.setReleaseDate((String) movieMap.get("release_date"));
        movieCard.setOverview((String) movieMap.get("overview"));
        movieCard.setPosterPath((String) movieMap.get("poster_path"));
        movieCard.setOriginalLanguage((String) movieMap.get("original_language"));
        movieCard.setPopularity((Double) movieMap.get("popularity"));
        movieCard.setVoteAverage((Double) movieMap.get("vote_average"));
        movieCard.setVoteCount((Integer) movieMap.get("vote_count"));
        return movieCard;
    }
}
