<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.chainsys.movieticket.model.MovieDetails"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Madurai Movie Booking</title>
<style>
body {
	background-image: url("picture/sc5.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	width: 80%;
	margin: 20px auto;
}

.movie-item {
	display: flex;
	justify-content: space-between;
	margin-bottom: 20px;
}

.movie-item img {
	width: 67%; /* Adjusted width */
	height: auto;
}

.theater {
	width: 60%;
	padding-left: 20px;
}

.showtime {
	margin-bottom: 10px;
}

.showtime-btn {
	background-color: black;
	color: white;
	border: none;
	padding: 8px 16px;
	border-radius: 5px;
	cursor: pointer;
	margin-right: 10px;
}
</style>
</head>
<body>
	<%
	session = request.getSession();
	ArrayList<MovieDetails> lists = (ArrayList<MovieDetails>) session.getAttribute("list");
	%>
	<div class="container">
		<form action="seat.jsp">
			<div class="movie-item">
				< class="movie-image"</a>
				<div class="theater">
				<%for(MovieDetails list : lists){ %>
					<h2><%= list.getTheaterName() %></h2>
					<%for(MovieDetails showTime : lists){ %>
						<%if(showTime.getTheaterName().equals(list.getTheaterName())){ %>
							<p>
							Show Times:
							<button type="submit" name="showtime" value="10:00 AM"
								class="showtime-btn"><%= showTime.getShowTime() %></button>
						</p>
						<%} %>
					<%} %>
				<%} %>
									</div>
				</div>
			</div>

			<!-- Hidden fields to store selected movie and other data -->
			<input type="hidden" name="movie" value="Movie 1" /> <input
				type="hidden" name="theater" value="Madurai Theatre 1" />
		</form>
	</div>
</body>
</html>
