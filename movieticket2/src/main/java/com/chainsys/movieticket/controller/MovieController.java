package com.chainsys.movieticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/search")
    public String searchMovies(@RequestParam("name") String movieName, Model model) {
        JsonNode searchResults = movieService.searchMoviesByName(movieName);
        String moviesJson = searchResults.toString();
        
        model.addAttribute("moviesJson", moviesJson);
        
        return "movieSearchResults";
    }

    @GetMapping("/movie")
    public String getMovieDetails(@RequestParam("id") int movieId, Model model) {
        JsonNode movie = movieService.getMovieDetails(movieId);
        System.out.println(movie);
        if (movie.isMissingNode()) {
            model.addAttribute("message", "Movie not found.");
        } else {
            model.addAttribute("movie", movie);
        }
        
        return "movieDetails";
    }
}
