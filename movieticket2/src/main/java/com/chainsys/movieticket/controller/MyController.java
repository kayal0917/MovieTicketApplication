package com.chainsys.movieticket.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

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

	@RequestMapping("/signup")
	public String signup(HttpSession session,Model model) {
		System.out.println("Home Page bye");
        session.setAttribute("theaterId", 43);
        List<ShowTime> showList = userDAO.fetchShowList(43);
        model.addAttribute("showList", showList);
		return "signUp";
	}
	@GetMapping("/userSignin")
	public String userSignin(@RequestParam("username") String username, @RequestParam("password") String password,HttpSession session) {
		System.out.println("signin Page");
		String location = userDAO.getuserLocation(username);
		System.out.println(location);
		List<MovieDetails> list = userDAO.getShowDetails(location);
		System.out.println(list.size());
		session.setAttribute("showList",list);
		return "home";
	}
	@GetMapping("/showList")
	public String showList() {
		System.out.println("showList Page");
		return "showList";
	}
	
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
		
		return "signin";
		
		
		
	}

	

	@GetMapping("/userList")
	public String getAllUser(Model model) {
		System.out.println("getting datas");
		List<Users> users = userDAO.getAllUsers();
		System.out.println(users);
		model.addAttribute("users", users);
		return "userList";
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
		return "userList";

	}

	@GetMapping("/search")
	public String search(@RequestParam("userName") String userName, Model model) {
		List<Users> users = userDAO.search(userName);
		model.addAttribute("users", users);

		return "userList";
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
	    public String login(@RequestParam("username") String username,
	                        @RequestParam("password") String password,
	                        HttpSession session, Model model) {
	        try {
	            if (username.equals("joe09@admin") && password.equals("Joe#09")) {
	                return "List";
	            } else {
	                session.setAttribute("adminId", 6);
	                session.setAttribute("theaterId", 43);
	                session.setAttribute("userName", username);
	                List<ShowTime> showList = userDAO.fetchShowList(43);
	                model.addAttribute("showList", showList);
	                return "showList";
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        return "adminsignin";
	    }
	

	@RequestMapping("/adminsign")
	public String registerUser(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("location") String location, @RequestParam("theater") String theaterName,
			HttpSession session) {

		userDAO.registerAdmin(userName, password, location, theaterName);
		return "adminSignin";
	}

	@RequestMapping("/adminHome")
	public String adminHome() {
		System.out.println("Home Page");
		return "adminHome";
	}
	@RequestMapping("/List")
	public String list() {
		System.out.println("Home Page");
		return "list";
	}

	@RequestMapping("/adminsignup")
	public String adminSigUp() {
		System.out.println("Home Page");
		return "adminsignup";
	}

	@GetMapping("/adminsignin")
	public String signin(@RequestParam("username") String userName, @RequestParam("password") String password, HttpSession session) {
		System.out.println(userName+" "+password);
		System.out.println(userDAO.adminpassword(userName));
		System.out.println(password.equals(userDAO.adminpassword(userName)));
			if (userName.equals("joe09admin") && password.equals("Joe#09")) {
				return "List";

			}

			else if (password.equals(userDAO.adminpassword(userName)))
			{
				
				String user = (String)session.getAttribute("userName");
				String location = (String)session.getAttribute("location");
				List<MovieDetails> list = userDAO.getShowDetails("Coimbatore");
				System.out.println(list.get(1));
				session.setAttribute("showList",list);
				System.out.println("Iam in the showlist");
				return "showList";
			}
			else {
				return "adminsignin";
			}
		}

	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("home Page");
		List<Movie> movie1 = userDAO.getAllMovie();
		System.out.println(movie1.get(0).getTrailerUrl());
		model.addAttribute("allmovies", movie1);
		return "home";
	}

	@GetMapping("/theaterList")
	public String getAllTheater(Model model) {
		System.out.println("getting theater datas");
		Theater theater = new Theater();
		List<Theater> theater1 = userDAO.getAllTheater();
		System.out.println(theater1);
		model.addAttribute("users", theater1);
		return "theaterList";
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
		return "theaterList";
	}

	@PostMapping("/movielist")
	public String MovieList(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("releaseDate") String releaseDate, @RequestParam("duration") int duration,
			@RequestParam("genre") String genre, @RequestParam("director") String director,
			@RequestParam("cast") String cast, @RequestParam("language") String language,
			@RequestParam("rating") double rating, @RequestParam("imageUrl") MultipartFile imageUrl,
			@RequestParam("trailerUrl") String trailerUrl) throws IOException {
		System.out.println(trailerUrl);
		byte[] imageBytes=null;
		if (!imageUrl.isEmpty()) {
			try {
                imageBytes = imageUrl.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
			Movie movie = new Movie();
			movie.setTitle(title);
			movie.setDescription(description);
			movie.setReleaseDate(releaseDate);
			movie.setDuration(duration);
			movie.setGenre(genre);
			movie.setDirector(director);
			movie.setCast(cast);
			movie.setLanguage(language);
			movie.setRating(rating);
			movie.setImageUrl(Base64.getEncoder().encodeToString(imageBytes));
			System.out.println(imageBytes);
			movie.setTrailerUrl(trailerUrl);
			userDAO.insertMovie(movie);

		} else {
			return "home";
		}

		return "home";
	}

	@RequestMapping("/AllMovie")
	public String AllMovie(Model model) {
		List<Movie> movie1 = userDAO.getAllMovie();
		System.out.println(movie1.get(0).getTrailerUrl());
		model.addAttribute("allmovies", movie1);
		return "/movielist";
	}
	@GetMapping("/addShow")
	public String addshow() {
		System.out.println("addshow Page");
		return "addshow";
	}
	@GetMapping("/movie")
	public String movie() {
		System.out.println("movie Page");
		return "movie";
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
//    @PostMapping("/addMovie")
//    public String movie(
//    		@RequestParam("title") String title, @RequestParam("description") String description,
//			@RequestParam("releaseDate") String releaseDate, @RequestParam("duration") int duration,
//			@RequestParam("genre") String genre, @RequestParam("director") String director,
//			@RequestParam("cast") String cast, @RequestParam("language") String language,
//			@RequestParam("rating") double rating, @RequestParam("imageUrl") String imageUrl,
//			@RequestParam("trailerUrl") String trailerUrl) {
//    	Movie movie=new Movie();
//        userDAO.insertMovie(movie.setTitle(title), movie.setDescription(description), movie.setReleaseDate(releaseDate), movie.setDuration(duration),
//				movie.setGenre(genre), movie.setDirector(director), movie.setCast(cast), movie.setLanguage(language), movie.setRating(rating),
//				movie.setImageUrl(imageUrl), movie.setTrailerUrl(trailerUrl));
//        return "addMovie";
//    }


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
//        for(ShowTime theater: showList) {
//			System.out.println(theater.getTheaterId());
//			for(ShowTime showTime: showList) {
//				if(theater.getTheaterId().equals(showTime.getTheaterId())) {
//					System.out.println(showTime.getShowtimeId());
//				}
//			}
//		}
//        System.out.println(theaterId);
        System.out.println(showList);
        model.addAttribute("showList", showList);
        return "showList";
    }

    @PostMapping("/processBooking")
    public String processBooking(
                                 @RequestParam("username") String userName,
                                 @RequestParam("selectedSeats") String seats,
                                 @RequestParam("selectedSeatsCount") int seatCount,
                                 @RequestParam("showDate") String bookingDate,
                                 @RequestParam("totalAmount") int totalAmount) {
    	
        userDAO.insertBooking( userName, seats, seatCount, bookingDate, totalAmount);
        return "payment";
    }
    
//    @RequestMapping("/Search")
//    public String propertySearch(Model model, @RequestParam("MovieName") String MovieName) {
//        List<Movie> list = userDAO.Search(MovieName).stream()
//                .filter(match -> match.getTitle().equalsIgnoreCase(MovieName) || match.getTitle().contains(MovieName))
//                .collect(Collectors.toList());
//        model.addAttribute("users", list);
//        return "RetrievePropertiesTable"; // Ensure this matches the actual JSP file name without .jsp
//    }

	@GetMapping("/control")
	public String index(Model model)
	{
		model.addAttribute("greetings", "Welcome");
		return"signin"; 
	}
	
	@RequestMapping("/adminSignIn")
	public String adminSignIns(Model model)
	{
		return "adminSignin";
	}
}
    
