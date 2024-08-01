package com.chainsys.movieticket.mapper;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.movieticket.model.Flim;
public class ImageMapper implements RowMapper<Flim>{




	    @Override
	    public Flim mapRow(ResultSet rs, int rowNum) throws SQLException {
	        
	    	Flim property = new Flim();
	        
	        Blob image = rs.getBlob("image_url");
	        if (image != null) 
	        {
	            int blobLength = (int) image.length();
	            byte[] blobAsBytes = image.getBytes(1, blobLength);
	            property.setImageUrl(blobAsBytes);
	        }
	        
	        return property;
	    }
}
