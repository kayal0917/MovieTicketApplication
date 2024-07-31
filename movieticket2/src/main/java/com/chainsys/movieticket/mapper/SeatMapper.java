package com.chainsys.movieticket.mapper;
import org.springframework.jdbc.core.RowMapper;

import com.chainsys.movieticket.model.Allocation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatMapper implements RowMapper<Allocation>  {


	
	    @Override
	    public Allocation mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Allocation seat = new Allocation();
	        seat.setUserName(rs.getString("user_name"));
	        seat.setSeat(rs.getString("seats"));
	        seat.setShowTime(rs.getString("showtime"));
	        seat.setTheaterId(rs.getInt("theater_id"));
	        seat.setShowDate(rs.getString("show_date"));
	        return seat;
	    }
	}


