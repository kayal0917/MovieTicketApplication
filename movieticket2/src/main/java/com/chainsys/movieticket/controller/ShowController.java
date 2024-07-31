package com.chainsys.movieticket.controller;

import java.util.Arrays;
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

import jakarta.servlet.http.HttpSession;

@Controller
public class ShowController {
	
	@Autowired
	UserDAO userDAO;
	
	@GetMapping("/showTime")
	public String showsInTheater(@RequestParam("movieName") String movieName,@RequestParam("ShowDate") String ShowDate,HttpSession session) {
		Allocation allocation =(Allocation) session.getAttribute("allocation");
		allocation.setBookingDate(ShowDate);
		allocation.setMovieName(movieName);
		List<MovieDetails> list = userDAO.getShowDetails(allocation.getLocation());
		Map<String, List<MovieDetails>> showTime = list.stream().filter(p->p.getTitle().equals(movieName)).collect(Collectors.groupingBy(MovieDetails::getTheaterName));
		session.setAttribute("allocation", allocation);
		session.setAttribute("showList", showTime);
		return "shows";
	}	
	@GetMapping("/seat")
    public String seat(@RequestParam("selectedShowTime")String showTime,@RequestParam("selectedShowDate")String showDate,@RequestParam("theaterId") int theaterId,Model model,HttpSession session) {
		Allocation allocation =(Allocation) session.getAttribute("allocation");
		List<Allocation> seatUsers = userDAO.seatUser();
		System.out.println(seatUsers);
		allocation.setBookingDate(showDate);
		allocation.setShowTime(showTime);
		allocation.setTheaterId(theaterId);
		session.setAttribute("allocation", allocation);
		char[] rows = {'A', 'B', 'C', 'D', 'E', 'F'};
		boolean[] isSeatFree = new boolean[rows.length * 20]; // Assuming 20 seats per row
		int seatIndex = 0;

		for (char row : rows) {
		    for (int i = 1; i <= 20; i++) {
		        String seat = "" + row + i;
		        boolean seatAllocated = false;

		        for (Allocation seatUser : seatUsers) {
		            if (seatUser.getShowDate().equals(showDate) && 
		                seatUser.getShowTime().equals(showTime) && 
		                seatUser.getSeat().equals(seat)) {
		                seatAllocated = true;
		                break;
		            }
		        }

		        isSeatFree[seatIndex] = !seatAllocated;
		        seatIndex++;
		    }
		}
		// Print seat allocation status for debugging
		for (int i = 0; i < isSeatFree.length; i++) {
		    System.out.println("Seat " + (i + 1) + " is " + (isSeatFree[i] ? "free" : "allocated"));
		}

		System.out.println(Arrays.toString(isSeatFree));
		model.addAttribute("rows", rows);
		model.addAttribute("isSeatFree",isSeatFree);
        return "seat"; 
    }
}
