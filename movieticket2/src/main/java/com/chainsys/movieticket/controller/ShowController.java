package com.chainsys.movieticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.chainsys.movieticket.dao.UserDAO;
import com.chainsys.movieticket.model.Theater;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShowController {
	
	@Autowired
	UserDAO userDAO;
	
	public String showsInTheater(HttpSession session) {
		int location=(int)session.getAttribute("location");
		List<Theater> allTheater = userDAO.getAllTheater();	
		userDAO.fetchShowList(location);
		return "shows";
	}
	
}
