package com.chainsys.movieticket.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.chainsys.movieticket.dao.UserDAO;
import com.chainsys.movieticket.model.Movie;
import com.chainsys.movieticket.model.MovieDetails;
import com.chainsys.movieticket.model.ShowTime;
import com.chainsys.movieticket.model.Theater;
import com.chainsys.movieticket.model.Users;
import com.chainsys.movieticket.validation.validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping("/signUp")
	public String signUp(@RequestParam("userName") String userName, @RequestParam("email") String email,
			@RequestParam("password") String password,@RequestParam("location") String location, Model model, HttpSession session) {
		if (!validation.isValidName(userName)) {
			model.addAttribute("error", "Invalid name format");

			return "errorPage.jsp";
		}

		if (!validation.isValidEmail(email)) {
			model.addAttribute("error", "Invalid phone number format");

			return "errorPage.jsp";
		}

		if (!validation.isValidPassword(password)) {
			model.addAttribute("error", "Invalid email format");

			return "errorPage.jsp";
		}

		Users users = new Users();
		users.setuserName(userName);
		users.setEmail(email);
		users.setPassword(password);
		users.setLocation(location);
		userDAO.insertUser(users);
		
		session.setAttribute(location, location);
		
		return "signin.jsp";
	}

	@RequestMapping("/signup")
	public String signup() {
		System.out.println("Home Page");
		return "signUp.jsp";
	}

	@GetMapping("/userList")
	public String getAllUser(Model model) {
		System.out.println("getting datas");
		List<Users> users = userDAO.getAllUsers();
		System.out.println(users);
		model.addAttribute("users", users);
		return "userList.jsp";
	}

	@GetMapping("/delete")
	public String removeUser(@RequestParam("userName") String userName, Model model) {
		Users users = new Users();
		users.setuserName(userName);
		userDAO.removeUser(users);

		System.out.println("getting datas");
		List<Users> users1 = userDAO.getAllUsers();
		System.out.println(users1);
		model.addAttribute("users", users1);
		return "userList.jsp";

	}

	@GetMapping("/search")
	public String search(@RequestParam("userName") String userName, Model model) {
		List<Users> users = userDAO.search(userName);
		model.addAttribute("users", users);

		return "userList.jsp";
	}

	@RequestMapping("/")
	public String index() {
		System.out.println("Home Page");
		return "index.jsp";
	}

//	@RequestMapping("admin")
//	public String admin() {
//		System.out.println("Home Page");
//		return "sigin.jsp";
//	}
//

	@PostMapping("/Login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session,Model model) {
		try {
			if (username.equals("joe09@admin") && password.equals("Joe#09")) {
				return "List.jsp";

			}

			else {
					session.setAttribute("adminId", 6);
					session.setAttribute("theaterId", 43);
					session.setAttribute("userName", username);

					List<ShowTime> showList = userDAO.fetchShowList(43);
					model.addAttribute("showList", showList);
					return "showList.jsp";
			}

		}

		catch (Exception e) {
			System.out.println(e);
		}
		return "AdminSignin.jsp";
	}

	@RequestMapping("/adminSignin")
	public String adminSignin() {
		System.out.println("Home Page");
		return "adminSignin.jsp";
	}

	@RequestMapping("/adminsignup")
	public String registerUser(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("location") String location, @RequestParam("theater") String theaterName,
			HttpSession session) {

		userDAO.registerAdmin(userName, password, location, theaterName);
		return "adminHome.jsp";
	}

	@RequestMapping("/adminHome")
	public String adminHome() {
		System.out.println("Home Page");
		return "adminHome.jsp";
	}

	@RequestMapping("/admin")
	public String adminSigUp() {
		System.out.println("Home Page");
		return "adminsignup.jsp";
	}

	@GetMapping("/signin")
	public String signin(@RequestParam("username") String userName, @RequestParam("password") String password, HttpSession session) {
		try {
			if (userName.equals("joe09admin") && password.equals("Joe#09")) {
				return "List.jsp";

			}

			else if (userName.equals(userDAO.adminusername(userName))
					&& password.equals(userDAO.adminpassword(userName))) 
			{				
				String user = (String)session.getAttribute("userName");
				String location = (String)session.getAttribute("location");
				List<MovieDetails> list = userDAO.getShowDetails(location);
				System.out.println(list.get(1));
				session.setAttribute("list",list);
				return "home.jsp";
			}

		}

		catch (Exception e) {
			System.out.println(e);
			return "signin.jsp";
		}
//        if (username.equals("admin") && password.equals("admin")) {
//            session.setAttribute("username", username);
//            return "redirect:/home";
//        } else {
//            
//        }
		return "signin.jsp";

	}

	@RequestMapping("/home")
	public String home() {
		System.out.println("home Page");
		return "home.jsp";
	}
	@GetMapping("/seat")
	public String seat() {
		System.out.println("seat Page");
		return "seat";
	}
	@GetMapping("/theaterList")
	public String getAllTheater(Model model) {
		System.out.println("getting theater datas");
		Theater theater = new Theater();
		List<Theater> theater1 = userDAO.getAllTheater();
		System.out.println(theater1);
		model.addAttribute("users", theater1);
		return "theaterList.jsp";
	}

	@GetMapping("/deleteTheater")
	public String removeTheater(@RequestParam("theatername") String theatername, Model model) {
		Theater theater = new Theater();
		theater.setTheatername(theatername);
		userDAO.removeTheater(theater);
		System.out.println("getting theater datas");
		userDAO.removeTheater(theater);
		List<Theater> theater1 = userDAO.getAllTheater();
		System.out.println(theater1);
		model.addAttribute("users", theater1);
		return "theaterList.jsp";
	}

	@PostMapping("/movielist")
	public String MovieList(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("releaseDate") String releaseDate, @RequestParam("duration") int duration,
			@RequestParam("genre") String genre, @RequestParam("director") String director,
			@RequestParam("cast") String cast, @RequestParam("language") String language,
			@RequestParam("rating") double rating, @RequestParam("imageUrl") MultipartFile imageUrl,
			@RequestParam("trailerUrl") String trailerUrl) throws IOException {
		System.out.println(trailerUrl);
		if (!imageUrl.isEmpty()) {
			Movie movie = new Movie();
			byte[] imageBytes = imageUrl.getBytes();
			movie.setTitle(title);
			movie.setDescription(description);
			movie.setReleaseDate(releaseDate);
			movie.setDuration(duration);
			movie.setGenre(genre);
			movie.setDirector(director);
			movie.setCast(cast);
			movie.setLanguage(language);
			movie.setRating(rating);
			movie.setImageUrl(imageBytes);
			System.out.println(imageBytes);
			movie.setTrailerUrl(trailerUrl);
			userDAO.insertMovie(movie);

		} else {
			return "home.jsp";
		}

		return "home.jsp";
	}

	@RequestMapping("/AllMovie")
	public String AllMovie(Model model) {
		List<Movie> movie1 = userDAO.getAllMovie();
		System.out.println(movie1.get(0).getTrailerUrl());
		model.addAttribute("users", movie1);
		return "movieList.jsp";
	}

	@PostMapping("/addShow")
	public String addShow(@RequestParam("MovieName") String movieName, @RequestParam("ShowDate") String showDate,
			@RequestParam("ShowTime") String showTime, HttpSession session, HttpServletRequest request) {
		Integer movieId = userDAO.fetchMovieIdByTitle(movieName);
		int theaterId=(int) session.getAttribute("theaterId");
		if (movieId == null) {
			request.setAttribute("message", "Movie not found");
			return "showResponse";
		}

		userDAO.insertShow(movieId, theaterId, showDate, showTime);

		request.setAttribute("message", "Show added successfully");
		return "showList";
	}

	@PostMapping("/deleteShow")
	public String deleteShow(@RequestParam("showtime_id") int showtimeId, HttpServletRequest request) {
		userDAO.deleteShow(showtimeId);
		return "showList";
	}

    @PostMapping("/showList")
    public String showList(HttpSession session,Model model) {
    	int theaterId = (int)session.getAttribute("theaterId");
    	System.out.println(theaterId);
        List<ShowTime> showList = userDAO.fetchShowList(theaterId);
        for(ShowTime theater: showList) {
			System.out.println(theater.getTheaterId());
			for(ShowTime showTime: showList) {
				if(theater.getTheaterId().equals(showTime.getTheaterId())) {
					System.out.println(showTime.getShowtimeId());
				}
			}
		}
        model.addAttribute("showList", showList);
        return "showList.jsp";
    }

    @PostMapping("/processBooking")
    public String processBooking(
                                 @RequestParam("username") String userName,
                                 @RequestParam("selectedSeats") String seats,
                                 @RequestParam("selectedSeatsCount") int seatCount,
                                 @RequestParam("showDate") String bookingDate,
                                 @RequestParam("totalAmount") int totalAmount) {
    	
        userDAO.insertBooking( userName, seats, seatCount, bookingDate, totalAmount);
        return "payment.jsp";
    }
//    @RequestMapping("/Search")
//    public String propertySearch(Model model, @RequestParam("MovieName") String MovieName) {
//        List<Movie> list = userDAO.Search(MovieName).stream()
//                .filter(match -> match.getTitle().equalsIgnoreCase(MovieName) || match.getTitle().contains(MovieName))
//                .collect(Collectors.toList());
//        model.addAttribute("users", list);
//        return "RetrievePropertiesTable"; // Ensure this matches the actual JSP file name without .jsp
//    }


    }
