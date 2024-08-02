package com.chainsys.movieticket.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chainsys.movieticket.dao.UserDAO;
import com.chainsys.movieticket.model.Allocation;
import com.chainsys.movieticket.model.Flim;
import com.chainsys.movieticket.model.Movie;
import com.chainsys.movieticket.model.MovieDetails;
import com.chainsys.movieticket.model.ShowTime;
import com.chainsys.movieticket.model.Theater;
import com.chainsys.movieticket.model.Users;
import com.chainsys.movieticket.validation.validation;

import jakarta.mail.Session;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@MultipartConfig
public class MyController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping("/signup")
	public String signup(HttpSession session, Model model) {
		System.out.println("Home Page bye");
		session.setAttribute("theaterId", 43);
		List<ShowTime> showList = userDAO.fetchShowList(43);
		model.addAttribute("showList", showList);
		return "signUp";
	}

	@GetMapping("/userSignin")
	public String userSignin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, Model model) {
		String location = userDAO.getuserLocation(username);
		System.out.println(location);
		Allocation allocation = new Allocation();
		allocation.setLocation(location);
		allocation.setUserName(username);
		
		session.setAttribute("allocation", allocation);
		Set<String> seenTitles = ConcurrentHashMap.newKeySet();
		List<MovieDetails> list = userDAO.getShowDetails(location).stream()
				.filter(movie -> seenTitles.add(movie.getTitle())).collect(Collectors.toList());
System.out.println(username);
		String getEmail=userDAO.getEmail(username);
		session.setAttribute("email", getEmail);
		System.out.println(getEmail);
		model.addAttribute("showList", list);
		return "index";
	}

	@GetMapping("/showList")
	public String showList() {
		System.out.println("showList Page");
		return "showList";
	}

	@PostMapping("/movie")
	public String addMovie() {
		return "movie";
	}
	@PostMapping("/ticket")
	public String ticket(HttpSession session) {
	    Allocation allocation = (Allocation) session.getAttribute("allocation");
	    
	    userDAO.insertBooking(allocation.getUserName(), allocation.getSeatCount(), allocation.getShowDate(), allocation.getTotalAmount());
	    
	    String[] seats = allocation.getSeat().split(",");
	    for (String seat : seats) {
	        userDAO.seat(allocation.getUserName(), seat, allocation.getShowTime(), allocation.getTheaterId(), allocation.getBookingDate());
	    }

	    String email = (String) session.getAttribute("email");
	    System.out.println(email);

	    return "ticket";
	}


	@GetMapping("/index")
	public String indexs() {
		return "index";
	}

	@RequestMapping("/signUp")
	public String signUp(@RequestParam("userName") String userName, @RequestParam("email") String email,
	        @RequestParam("password") String password, @RequestParam("location") String location, Model model,
	        HttpSession session) {
	    
	    if (!validation.isValidName(userName)) {
	        model.addAttribute("error", "Invalid name format");
	        return "errorPage.jsp";
	    }

	    if (!validation.isValidEmail(email)) {
	        model.addAttribute("error", "Invalid email format");
	        return "errorPage.jsp";
	    }

	    if (!validation.isValidPassword(password)) {
	        model.addAttribute("error", "Invalid password format");
	        return "errorPage.jsp";
	    }

	    Users users = new Users();
	    users.setuserName(userName);
	    users.setEmail(email);
	    users.setPassword(password);
	    users.setLocation(location);

	    userDAO.insertUser(users);

	    session.setAttribute("location", location);
	    session.setAttribute("email", email);
	    
	    System.out.println(email);

	    return "signin";
	}

	@GetMapping("/userList")
	public String getAllUser(Model model) {
		System.out.println("getting datas");
		List<Users> users = userDAO.getAllUsers();
		model.addAttribute("users", users);
		return "userList";
	}

	@GetMapping("/delete")
	public String removeUser(@RequestParam("userName") String userName, Model model) {
		Users users = new Users();
		users.setuserName(userName);
		userDAO.removeUser(users);

		List<Users> users1 = userDAO.getAllUsers();
		model.addAttribute("users", users1);
		return "userList";

	}

	/*
	 * @GetMapping("/search") public String search(@RequestParam("userName") String
	 * userName, Model model) { List<Users> users = userDAO.search(userName);
	 * model.addAttribute("users", users);
	 * 
	 * return "userList"; }
	 */
	

//	@RequestMapping("admin")
//	public String admin() {
//		System.out.println("Home Page");
//		return "sigin.jsp";
//	}
//

	@PostMapping("/Login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, Model model) {
		try {
			if (username.equals("joe09admin") && password.equals("Joe#09")) {
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
		return "adminHome";
	}

	@RequestMapping("/List")
	public String list() {
		return "list";
	}

	@RequestMapping("/adminsignup")
	public String adminSigUp() {
		return "adminSignUp";
	}

	@RequestMapping("/adminDasboard")
	public String adminDasboard() {
		return "adminDasboard";
	}

	@GetMapping("/adminsignin")
	public String signin(@RequestParam("username") String userName, @RequestParam("password") String password,
			Model model, HttpSession session) {
		if (userName.equals("joe09admin") && password.equals("Joe#09")) {
			return "List";
		} else if (password.equals(userDAO.adminpassword(userName))) {
			int theaterId = userDAO.findTheaterIdByName(userName);
			session.setAttribute("theaterId", theaterId);
			List<ShowTime> showList = userDAO.fetchShowList(theaterId);
			/*
			 * System.out.println("setting the showlist");
			 */			model.addAttribute("showList", showList);
			return "adminDasboard";
		} else {
			return "adminsignin";
		}
	}

	/*
	 * @RequestMapping("/home") public String home(Model model) {
	 * System.out.println("home Page"); List<Movie> movie1 = userDAO.getAllMovie();
	 * System.out.println(movie1.get(0).getTrailerUrl());
	 * model.addAttribute("allmovies", movie1); return "home"; }
	 */
	@GetMapping("/theaterList")
	public String getAllTheater(Model model) {
		System.out.println("getting theater datas");
		Theater theater = new Theater();
		List<Theater> theater1 = userDAO.getAllTheater();
		model.addAttribute("users", theater1);
		return "theaterList";
	}

	@GetMapping("/deleteTheater")
	public String removeTheater(@RequestParam("theatername") String theatername, Model model) {
		Theater theater = new Theater();
		theater.setTheatername(theatername);
		userDAO.removeTheater(theater);
		userDAO.removeTheater(theater);
		List<Theater> theater1 = userDAO.getAllTheater();
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
		byte[] imageBytes = null;
		if (!imageUrl.isEmpty()) {
			try {
				imageBytes = imageUrl.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			}
			Flim movie = new Flim();
			
			byte[] image = imageUrl.getBytes();
			movie.setTitle(title);
			movie.setDescription(description);
			movie.setReleaseDate(releaseDate);
			movie.setDuration(duration);
			movie.setGenre(genre);
			movie.setDirector(director);
			movie.setCast(cast);
			movie.setLanguage(language);
			movie.setRating(rating);
			movie.setImageUrl(image);
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
		List<Flim> movie1 = userDAO.getAllMovie();
		for(Flim object:movie1)
        {
            byte[] document = object.getImageUrl();
            String getDocument = Base64.getEncoder().encodeToString(document);
            object.setBase64(getDocument);
        }
		model.addAttribute("allmovies", movie1);
		
		return "movieList";
	}

	@GetMapping("/addShow")
	public String addshow() {
		return "addshow";
	}
	@PostMapping("/view")
	  public String view(Model model, @RequestParam("title") String title)
    {
        List<Flim> getImage = userDAO.viewImage(title);
        for(Flim property:getImage)
        {
            byte[] getImage1 = property.getImageUrl();
            String toBase = Base64.getEncoder().encodeToString(getImage1);
            property.setBase64(toBase);
        }
        model.addAttribute("allmovies", getImage);
        return "ImageView";
    }

	/*
	 * @GetMapping("/movie") public String movie() {
	 * System.out.println("movie Page"); return "movie"; }
	 */
	@PostMapping("/payment")
	public String payment() {
		return "payment";
	}

	@PostMapping("/paymentprocess")
	public String paymentprocess() 
	{
		return "paymentprocess";
	}

	@PostMapping("/addShow")
	public String addShow(@RequestParam("MovieName") String movieName, @RequestParam("ShowDate") String showDate,
			@RequestParam("ShowTime") String showTime, HttpSession session, Model model) {
		Integer movieId = userDAO.fetchMovieIdByTitle(movieName);
		int theaterId = (int) session.getAttribute("theaterId");
		if (movieId == null) {
			model.addAttribute("message", "Movie not found");
			return "showResponse";
		}
		userDAO.insertShow(movieId, theaterId, showDate, showTime);

		model.addAttribute("message", "Show added successfully");
		return "showList";
	}

	@PostMapping("/deleteShow")
	public String deleteShow(@RequestParam("showtime_id") int showtimeId, HttpServletRequest request) {
		userDAO.deleteShow(showtimeId);
		return "showList";
	}

	@PostMapping("/showList")
	public String showList(HttpSession session, Model model) {
		int theaterId = (int) session.getAttribute("theaterId");
		List<ShowTime> showList = userDAO.fetchShowList(theaterId);
		for (ShowTime theater : showList) {
			for (ShowTime showTime : showList) {
				if (theater.getTheaterId().equals(showTime.getTheaterId())) {
				}
			}
		}
	
		model.addAttribute("showList", showList);
		return "showList";
	}

	@PostMapping("/processBooking")
	public String processBooking(@RequestParam("selectedSeats") String seats,
			@RequestParam("selectedSeatsCount") int seatCount, HttpSession session) {

		Allocation allocation = (Allocation) session.getAttribute("allocation");
		allocation.setSeat(seats);
		session.setAttribute("seats", seats);
		allocation.setSeatCount(seatCount);
		allocation.setTotalAmount(seatCount * 200);
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

	@GetMapping("/control" )
	public String index(HttpSession session, Model model) {
		model.addAttribute("greetings", "Welcome");
		return "signin";
	}

	@RequestMapping("/adminSignIn")
	public String adminSignIns(Model model) {
		return "adminSignin";
	}

	@RequestMapping("/front")
	public String front() {
		return "front";
	}

}
