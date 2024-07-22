package com.chainsys.movieticket.mapper;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.movieticket.model.Movie;

public class MovieMapper implements RowMapper<Movie> {
	   public Movie mapRow(ResultSet rs, int rowNum) throws SQLException 
	    {
	    	Movie movie=new Movie();
	    	String title=rs.getString("title");
	        String description=rs.getString("description");
	        String releaseDate=rs.getString("release_date");
	        int duration=rs.getInt("duration");
	        String genre=rs.getString("genre");
	        String director=rs.getString("director");
	        String cast=rs.getString("cast");
	        String language=rs.getString("language");
	        double rating=rs.getDouble("rating");
	        String imageUrl=Base64.getEncoder().encodeToString(rs.getBytes("image_url"));
	        
	        String trailerUrl=rs.getString("trailer_url");


	     movie.setTitle(title);
	     movie.setDescription(description);
	     movie.setReleaseDate(releaseDate);
	     movie.setDuration(duration);
	     movie.setGenre(genre);
	     movie.setDirector(director);
	     movie.setCast(cast);
	     movie.setLanguage(language);
	     movie.setRating(rating);
	     movie.setImageUrl(imageUrl);
	     movie.setTrailerUrl(trailerUrl);
	        return movie;
	    }
}
