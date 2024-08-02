package com.chainsys.movieticket.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.movieticket.model.Movie;
import com.chainsys.movieticket.model.MovieCard;
import com.chainsys.movieticket.service.MovieService;


@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/byId")
    public String getMovieById(@RequestParam("imdbId") String imdbId, Model model) {
        Movie movie = movieService.getMovieDetailsById(imdbId);
        model.addAttribute("movie", movie);
        return "movieDetails";
    }

    @GetMapping("/movie/byTitle")
    public String getMovieByTitle(@RequestParam("title") String title,String Date ,Model model) {
        Movie movie = movieService.getMovieDetailsByTitle(title);
        
        model.addAttribute("movie", movie);
        return "movieDetail";
    }

//    @GetMapping("/movie/byTitleAndYear")
//    public String getMovieByTitleAndYear(@RequestParam("title") String title, Model model) {
//    	List<Map<String, Object>> movies = movieService.getMoviesByYearWithDetails("2024");
//        for (Map<String, Object> movieNames : movies) {
//            System.out.println(movieNames.get("title") + " - " + movieNames.get("release_date"));
//            Movie movie = movieService.getMovieDetailsByTitleAndYear(movieNames.get("title").toString(), "2024");
//        }
//        Movie movie = movieService.getMovieDetailsByTitleAndYear(title, "2024");
//        model.addAttribute("movie", movie);
//        return "movieDetails";
//    }
    
    @GetMapping("/movie/byTitleAndYear")
    public String getMovieByTitleAndYear(Model model) {
        List<Map<String, Object>> movies = movieService.getMoviesByYear("2024");
        List<MovieCard> movieList = new ArrayList<>();

        for (Map<String, Object> movieNames : movies) {
            MovieCard movie = movieService.getMovieDetailsByTitleAndYear1(movieNames.get("title").toString(), "2024");
            movieList.add(movie);
        }

        model.addAttribute("movies", movieList);
        return "movieCards";
    }

    @GetMapping("/movie/search")
    public String searchMovies(@RequestParam("title") String title, Model model) {
        Movie movie = movieService.searchMoviesByTitle(title);
        if (movie != null) {
            model.addAttribute("movie", movie);
            model.addAttribute("showBookNow", true);
        } else {
            model.addAttribute("showBookNow", false);
        }
        return "movieDetails"; 
    
       
    }
    @GetMapping("/search")
    public String searchMovies() {
        return "search";
    }
    
//    @GetMapping("/getMovieByYear")
//    public void getMovieByYear() {
//    	System.out.println(" by year");
//    	List<Map<String, Object>> movies = movieService.getMoviesByYearWithDetails("2023");     
//        for (Map<String, Object> movie : movies) {
//            System.out.println(movie.get("title") + " - " + movie.get("release_date"));
//        }
//
//    }
}
