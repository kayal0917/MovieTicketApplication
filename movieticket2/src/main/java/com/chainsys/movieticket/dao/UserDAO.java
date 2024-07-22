package com.chainsys.movieticket.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.chainsys.movieticket.model.Movie;
import com.chainsys.movieticket.model.MovieDetails;
import com.chainsys.movieticket.model.ShowTime;
import com.chainsys.movieticket.model.Theater;
import com.chainsys.movieticket.model.Users;

@Repository
public interface UserDAO {
    public void registerAdmin(String userName, String hashedPassword, String location, String theaterName);
    public void insertUser(Users users) ;
	public List<Users> getAllUsers();
	public void removeUser(Users users);
	List<Users> search(String userName);
	public List<Theater> getAllTheater() ;
	public void removeTheater(Theater theater) ;
	public Object adminpassword(String username);
	public Object adminusername(String username);
	public String getuserLocation(String username);
	List<Movie> getAllMovie();
	public void insertShowtime(int movieId, int theaterId, String showDate, String showTime) ;
	public Integer findMovieIdByTitle(String title) ;
	void insertMovie(Movie movie);
	public void insertShow(Integer movieId, Integer theaterId, String showDate, String showTime) ;
	public void deleteShow(int showtimeId) ;
	public List<ShowTime> fetchShowList(int theaterId) ;
	public Integer fetchMovieIdByTitle(String movieName);
	public List<MovieDetails> getShowDetails(String location) ;
	public void insertBooking(String userName, String seats, int seatCount, String bookingDate,
			int totalAmount);


}
