package com.chainsys.movieticket.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.movieticket.model.MovieDetails;

public class ShowDetailsMapper implements RowMapper<MovieDetails> {
	   public MovieDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	MovieDetails details=new MovieDetails();
	    	details.setShowtimeId(rs.getInt("showtime_id"));
	    	details.setMovieId(rs.getInt("movie_id"));
	    	details.setTitle(rs.getString("title"));
	    	details.setDescription(rs.getString("description"));
	    	details.setReleaseDate(rs.getString("release_date"));
	    	details.setDuration(rs.getInt("duration"));
	    	details.setGenre(rs.getString("genre"));
	    	details.setDirector(rs.getString("director"));
	    	details.setCast(rs.getString("cast"));
	    	details.setLanguage(rs.getString("language"));
	    	details.setRating(rs.getDouble("rating"));
	    	details.setTrailerUrl(rs.getString("trailer_url"));
	    	details.setImageUrl(Base64.getEncoder().encodeToString(rs.getBytes("image_url")));
	    	details.setTheaterId(rs.getInt("theater_id"));
	        details.setCityId(rs.getInt("city_id"));
	        details.setTheaterName(rs.getString("theater_name"));
	        details.setShowDate(rs.getString("show_date"));
	        details.setShowTime(rs.getString("show_time"));
	        details.setTheaterDeleteUser(rs.getString("theater_delete_user"));
	        details.setAdminId(rs.getInt("admin_id"));
	        return details;
	    }
	   }


