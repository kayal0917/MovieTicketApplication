package com.chainsys.movieticket.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.movieticket.mapper.FlimMapper;
import com.chainsys.movieticket.mapper.ImageMapper;
import com.chainsys.movieticket.mapper.SeatMapper;
//import com.chainsys.movieticket.mapper.MovieMapper;
import com.chainsys.movieticket.mapper.ShowDetailsMapper;
import com.chainsys.movieticket.mapper.TheaterMapper;
import com.chainsys.movieticket.mapper.UserMapper;
import com.chainsys.movieticket.model.Allocation;
import com.chainsys.movieticket.model.Flim;
import com.chainsys.movieticket.model.Movie;
import com.chainsys.movieticket.model.MovieDetails;
import com.chainsys.movieticket.model.ShowTime;
import com.chainsys.movieticket.model.Theater;
import com.chainsys.movieticket.model.Users;

import jakarta.servlet.annotation.MultipartConfig;

@Repository
@MultipartConfig
public class MovieImp implements UserDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	UserMapper mapper;
	FlimMapper flim;

	TheaterMapper theatermapper;
	ImageMapper imageMapper;

	public String adminpassword(String username) {
		String adminPassword = "select password from admin where user_name=? ";
		String password = null;
		try {
			password = jdbcTemplate.queryForObject(adminPassword, String.class, username);
		} catch (Exception e) {
			System.out.println(e);
		}
		return password;
	}

	public String getuserLocation(String username) {
		String query = "select location from users where user_name=? ";
		String location = null;
		try {
			location = jdbcTemplate.queryForObject(query, String.class, username);
		} catch (Exception e) {
			System.out.println(e);
		}
		return location;
	}

//	public String getadminLocation(String username) {
//		String query = "select location from  where user_name=? ";
//		String location = null;
//		try {
//			location = jdbcTemplate.queryForObject(query, String.class, username);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		return location;
//	}
	public String adminusername(String username) {
		String adminusername = "select user_name from users where user_name=? and delete_user=0";
		String password = null;
		try {
			password = jdbcTemplate.queryForObject(adminusername, String.class, username);
		} catch (Exception e) {
			System.out.println(e);
		}
		return password;
	}

	@Override
	public void registerAdmin(String userName, String password, String location, String theaterName) {
		String INSERT_ADMIN_SQL = "INSERT INTO admin (user_name, password) VALUES (?, ?)";
		String INSERT_THEATER_SQL = "INSERT INTO theaters (theater_name, city_id,admin_id) VALUES (?,?,?)";
		jdbcTemplate.update(INSERT_ADMIN_SQL, userName, password);
		int adminid = getAdminId();
		int cityId = getCityId(location);
		jdbcTemplate.update(INSERT_THEATER_SQL, theaterName, cityId, adminid);
	}

	public int getAdminId() {
		String getAdminId = "SELECT MAX(admin_id) AS last_admin_id FROM admin";
		Integer adminId = jdbcTemplate.queryForObject(getAdminId, Integer.class);
		return adminId != null ? adminId : 0;
	}

	private int getCityId(String location) {
		String SELECT_LOCATION_ID_SQL = "SELECT city_id FROM cities WHERE city_name = ?";
		return jdbcTemplate.queryForObject(SELECT_LOCATION_ID_SQL, Integer.class, location);
	}

	public void insertUser(Users users) {
		String insert = "insert into users(user_name, email,password,location) values (?,?,?,?)";
		Object[] params = { users.getuserName(), users.getEmail(), users.getPassword(), users.getLocation() };
		jdbcTemplate.update(insert, params);

	}

	@Override
	public List<Users> getAllUsers() {
		String select = "select user_name,email,password,location from users where delete_user=0";
		List<Users> userList = jdbcTemplate.query(select, new UserMapper());
		return userList;
	}

	public void removeUser(Users users) {
		String delete = "update users set delete_user=1 where user_name=?";
		Object[] userName = { users.getuserName() };
		jdbcTemplate.update(delete, userName);

	}

	@Override
	public List<Users> search(String userName) {
		String search = String.format("SELECT user_name,email,password FROM users "
				+ "WHERE (user_name LIKE '%%%s%%' OR email LIKE '%%%s%%'OR password LIKE '%%%s%%') "
				+ "AND delete_user=0", userName, userName, userName);
		return jdbcTemplate.query(search, new UserMapper());
	}

	public List<Theater> getAllTheater() {
		String select = "SELECT t.theater_id, c.city_name, t.theater_name\r\n" + "FROM theaters t\r\n"
				+ "INNER JOIN cities c ON t.city_id = c.city_id && t.delete_user=0;";
		List<Theater> theaterList = jdbcTemplate.query(select, new TheaterMapper());
		return theaterList;
	}

	public void removeTheater(Theater theater) {
		String delete = "update theaters set delete_user=1 where theater_name=?";
		Object[] theatername = { theater.getTheatername() };
		jdbcTemplate.update(delete, theatername);

	}

	@Override
	public void insertMovie(Flim movie) {
		String insertmovie = "INSERT INTO movie_details (title, description, release_date, duration, genre, director, cast, language, rating, image_url, trailer_url) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { movie.getTitle(), movie.getDescription(), movie.getReleaseDate(), movie.getDuration(),
				movie.getGenre(), movie.getDirector(), movie.getCast(), movie.getLanguage(), movie.getRating(),
				movie.getImageUrl(), movie.getTrailerUrl() };
		jdbcTemplate.update(insertmovie, params);
	}

	public Integer findMovieIdByTitle(String title) {
		String query = "SELECT movie_id FROM movie_details WHERE title = ?";
		return jdbcTemplate.queryForObject(query, new Object[] { title }, Integer.class);
	}

	public void insertShowtime(int movieId, int theaterId, String showDate, String showTime) {
		String insertShowtime = "INSERT INTO showtimes (movie_id, theater_id, show_date, show_time) VALUES (?,?,?,?)";
		jdbcTemplate.update(insertShowtime, movieId, theaterId, Date.valueOf(showDate), Time.valueOf(showTime));
	}

	@Override
	public List<Flim> getAllMovie() {
		String select = "select title, description, release_date, duration, genre, director, cast, language, rating, image_url, trailer_url from movie_details where delete_user=0  ";
		List<Flim> movieList = jdbcTemplate.query(select, new FlimMapper());
		return movieList;
	}

	public Integer fetchMovieIdByTitle(String movieName) {
		String fetchMovieIdSql = "SELECT movie_id FROM movie_details WHERE title = ?";
		return jdbcTemplate.queryForObject(fetchMovieIdSql, new Object[] { movieName }, Integer.class);
	}

	public void insertShow(Integer movieId, Integer theaterId, String showDate, String showTime) {
		String insertShowSql = "INSERT INTO show_table (movie_id, theater_id, show_date, show_time) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(insertShowSql, movieId, theaterId, showDate, showTime);
	}

	public void deleteShow(int showtimeId) {
		String deleteShowSql = "DELETE FROM show_table WHERE showtime_id = ?";
		jdbcTemplate.update(deleteShowSql, showtimeId);
	}

	public int findTheaterIdByName(String username) {
		String query = "SELECT theater_id FROM theaters WHERE admin_id=(SELECT admin_id FROM admin WHERE user_name=?);";
		return jdbcTemplate.queryForObject(query, Integer.class, new Object[] { username });
	}

	public List<ShowTime> fetchShowList(int theaterId) {
		String fetchShowListSql = "SELECT s.showtime_id, m.title AS movieName, s.theater_id, s.show_date, s.show_time "
				+ "FROM show_table s " + "JOIN movie_details m ON s.movie_id = m.movie_id WHERE s.theater_id="
				+ theaterId;
		return jdbcTemplate.query(fetchShowListSql, (rs, rowNum) -> {
			ShowTime showTime = new ShowTime();
			showTime.setShowtimeId(rs.getInt("showtime_id"));
			showTime.setMovieName(rs.getString("movieName"));
			showTime.setTheaterId(rs.getInt("theater_id"));
			showTime.setShowDate(rs.getString("show_date"));
			showTime.setShowTime(rs.getString("show_time"));
			return showTime;
		});
	}

	public List<MovieDetails> getShowDetails(String location) {
		String select = "SELECT " + "    st.showtime_id, " + "    st.movie_id, " + "    md.title, "
				+ "    md.description, " + "    md.release_date, " + "    md.duration, " + "    md.genre, "
				+ "    md.director, " + "    md.cast, " + "    md.language, " + "    md.rating, "
				+ "    md.trailer_url, " + "    md.image_url, " + "    st.theater_id, " + "    t.city_id, "
				+ "    t.theater_name, " + "    st.show_date, " + "    st.show_time, "
				+ "    t.delete_user AS theater_delete_user, " + "    t.admin_id " + "FROM " + "    show_table st "
				+ "INNER JOIN " + "    theaters t ON st.theater_id = t.theater_id " + "INNER JOIN "
				+ "    movie_details md ON st.movie_id = md.movie_id " + "WHERE " + "    t.city_id = ? "
				+ "LIMIT 0, 1000;";

		int cityId = getCityId(location);
		@SuppressWarnings("deprecation")
		List<MovieDetails> detailsList = jdbcTemplate.query(select, new Object[] { cityId }, new ShowDetailsMapper());
		return detailsList;
	}

	public List<MovieDetails> getShowDetails(String location, String movieName) {
		String select = "SELECT " + "    st.showtime_id, " + "    st.movie_id, " + "    md.title, "
				+ "    md.description, " + "    md.release_date, " + "    md.duration, " + "    md.genre, "
				+ "    md.director, " + "    md.cast, " + "    md.language, " + "    md.rating, "
				+ "    md.trailer_url, " + "    md.image_url, " + "    st.theater_id, " + "    t.city_id, "
				+ "    t.theater_name, " + "    st.show_date, " + "    st.show_time, "
				+ "    t.delete_user AS theater_delete_user, " + "    t.admin_id " + "FROM " + "    show_table st "
				+ "INNER JOIN " + "    theaters t ON st.theater_id = t.theater_id " + "INNER JOIN "
				+ "    movie_details md ON st.movie_id = md.movie_id " + "WHERE "
				+ "    t.city_id = ? AND md.title = ?  " + "LIMIT 0, 1000;";

		int cityId = getCityId(location);
		@SuppressWarnings("deprecation")
		List<MovieDetails> detailsList = jdbcTemplate.query(select, new Object[] { cityId, movieName },
				new ShowDetailsMapper());
		return detailsList;
	}

	@Override
	public void insertBooking(String userName, int seatCount, String bookingDate, int totalAmount) {
		String sql = "INSERT INTO bookings (user_name,seat_count, booking_date, total_amount) " + "VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sql, userName, seatCount, bookingDate, totalAmount);
	}

	@Override
	public List<Allocation> seatUser() {
		String select = "SELECT  user_name, seats, showtime, theater_id, show_date FROM seats";
		List<Allocation> bookingList = jdbcTemplate.query(select, new SeatMapper());
		return bookingList;
	}

	public void seat(String userName, String seats, String showtime, int theaterId, String showDate) {
		String sql = "INSERT INTO seats ( user_name, seats, showtime, theater_id, show_date) "
				+ "VALUES ( ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, userName, seats, showtime, theaterId, showDate);
	}

	@Override
	public List<Flim> viewImage(String title) 
    {
        String retrive = "select image_url from movie_details where title=?";
        List<Flim> list = jdbcTemplate.query(retrive, new ImageMapper(), title);
        return list;
    }

	@Override
	public String getEmail(String username) {
		String email = "select email from users where user_name=? ";
		
		return jdbcTemplate.queryForObject(email, String.class,username);
	}


	/*
	 * public void insertBooking(String userName, int seatCount, double totalAmount)
	 * { String sql =
	 * "INSERT INTO bookings (user_name, seat_count, total_amount) VALUES (?, ?, ?)"
	 * ; jdbcTemplate.update(sql, userName, seatCount, totalAmount); }
	 */

}
