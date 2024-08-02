package com.chainsys.movieticket.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.chainsys.movieticket.model.Flim;
public class FlimMapper implements RowMapper<Flim> {


	
	    @Override
	    public Flim mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Flim movie = new Flim();
	        movie.setTitle(rs.getString("title"));
	        movie.setDescription(rs.getString("description"));
	        movie.setReleaseDate(rs.getString("release_date"));
	        movie.setDuration(rs.getInt("duration"));
	        movie.setGenre(rs.getString("genre"));
	        movie.setDirector(rs.getString("director"));
	        movie.setCast(rs.getString("cast"));
	        movie.setLanguage(rs.getString("language"));
	        movie.setRating(rs.getDouble("rating"));
	        movie.setImageUrl(rs.getBytes("image_url"));
	        movie.setTrailerUrl(rs.getString("trailer_url"));
	        return movie;
	    }
	}


