package com.chainsys.movieticket.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chainsys.movieticket.dao.UserDAO;
import com.chainsys.movieticket.model.Allocation;
import com.chainsys.movieticket.model.MovieDetails;
import com.chainsys.movieticket.model.ShowTime;
import com.chainsys.movieticket.model.Theater;

import jakarta.servlet.http.HttpSession;

@Controller
public class ShowController {
	
	@Autowired
	UserDAO userDAO;
	
	@GetMapping("/showTime")
	public String showsInTheater(String movieName,HttpSession session) {
		Allocation allocation =(Allocation) session.getAttribute("allocation");
		List<MovieDetails> list = userDAO.getShowDetails(allocation.getLocation());
		System.out.println(allocation.getLocation());
		Map<String, List<MovieDetails>> showTime = list.stream().filter(p->p.getTitle().equals(movieName)).collect(Collectors.groupingBy(MovieDetails::getTheaterName));
		/*
		 * showTime.forEach((show,Movie)->{ System.out.println(show);
		 * Movie.forEach(p->System.out.println(p.getShowTime())); });
		 */
		session.setAttribute("showList", showTime);
		return "shows";
	}	
	@GetMapping("/seat")
    public String seat(@RequestParam("selectedShowTime")String showTime,Model model) {
		char[] rows = {'A', 'B', 'C', 'D', 'E', 'F'};
		model.addAttribute("rows", rows);
        return "seat"; 
    }
}
