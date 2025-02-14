<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.chainsys.movieticket.model.MovieDetails"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Movie List</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>




body {
	background: #FFF linear-gradient(to bottom, #3F567C, #B5CCC6) no-repeat;
	font-family: 'Open Sans', sans-serif;
	
}

.window-margin {
	max-width: 1200px;
	padding: 60px;
	margin: auto;
}

.window {
	margin: auto;
	border-radius: 5px;
	background: #FFF;
	overflow: hidden;
	position: relative;
	box-shadow: 0 0 5px rgba(0, 0, 0, .1);
}

.main {
	position: relative;
}

.main .top-bar {
	background: #279CEB;
	height: 65px;
	color: #FFF;
}

.main .top-bar .profile-box {
	float: right;
	background: #3AB0FF;
	height: 65px;
	line-height: 65px;
	padding: 0 20px;
	cursor: pointer;
}

.main .top-bar .profile-box .circle {
	background: #FFF;
	height: 30px;
	width: 30px;
	border-radius: 50px;
	display: block;
	float: left;
	margin-top: 15px;
}

.main .top-bar .profile-box .arrow {
	float: left;
	margin-left: 10px;
	line-height: 65px;
}

.main .top-bar .top-menu {
	height: 65px;
	overflow: hidden;
}

.main .top-bar .top-menu li {
	float: left;
	font-size: 14px;
}

.main .top-bar .top-menu a {
	padding: 0 25px;
	color: #EEE;
	line-height: 65px;
	display: block;
}

.main .top-bar .top-menu a:hover {
	color: #FFF;
	background: rgba(58, 176, 255, 0.35);
}

.main .top-bar .top-menu li.active a {
	background: #3AB0FF;
	color: #FFF;
	font-weight: bold;
}

.featured-movie {
	position: relative;
}

.featured-movie .cover {
	width: 100%;
	display: block;
	max-height: 360px;
}

.featured-movie .corner-title {
	position: absolute;
	top: 20px;
	left: 20px;
	text-transform: uppercase;
	color: #FFF;
	background: rgba(25, 25, 25, .3);
	padding: 10px;
	font-size: 13px;
}

.featured-movie .bottom-bar {
	position: absolute;
	bottom: 0;
	left: 0;
	right: 0;
	padding: 20px 30px;
	background: rgba(10, 10, 10, .8);
	color: #FFF;
	overflow: hidden;
}

.featured-movie .bottom-bar .title-container {
	float: left;
	font-weight: 100;
	font-size: 40px;
}

.featured-movie .bottom-bar .title-container .fa {
	margin-right: 10px;
	color: #FF3A3A;
}

.featured-movie .bottom-bar .title-container b {
	font-weight: 600;
}

.featured-movie .bottom-bar .right {
	float: right;
	font-size: 14px;
	margin-top: 14px;
}

.featured-movie .bottom-bar .right .stars {
	float: left;
	color: #F0C236;
	margin-right: 20px;
}

.featured-movie .bottom-bar .right .share {
	float: left;
	cursor: pointer;
}

.featured-movie .bottom-bar .right .share .fa {
	margin-right: 5px;
}

.movie-list .title-bar {
	padding: 20px;
	border-bottom: 1px solid #DDD;
	overflow: hidden;
}

.movie-list .title-bar .left {
	float: left;
	font-size: 15px;
	text-transform: uppercase;
}

.movie-list .title-bar .left .grey {
	color: #999;
}

.movie-list .title-bar .left .bold {
	font-weight: bold;
}

.movie-list .title-bar .right {
	float: right;
}

.movie-list .title-bar .right a {
	color: #999;
	margin-left: 10px;
}

.movie-list .title-bar .right a.blue {
	color: #279CEB;
}

.movie-list .list {
	margin: 20px;
	margin-right: 0;
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
}

.movie-list .list li {
	flex: 0 0 130px;
	padding-bottom: 30px;
	margin-right: 20px;
	position: relative;
}

.movie-list .list li:after {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	height: 195px;
	content: '\f144';
	background: rgba(0, 0, 0, .3);
	border-radius: 5px;
	opacity: 0;
	color: #FFF;
	font-size: 40px;
	display: block;
	cursor: pointer;
	line-height: 195px;
	text-align: center;
	font-family: FontAwesome;
	font-style: normal;
	font-weight: normal;
	-webkit-font-smoothing: antialiased;
}

.movie-list .list li img {
	width: 130px;
	height: 195px;
	display: block;
	margin: 0 auto 5px auto;
	cursor: pointer;
}

.movie-list .list li .title, .movie-list .list li .genre {
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	cursor: pointer;
}

.movie-list .list li .title {
	font-weight: bold;
	font-size: 13px;
	margin-bottom: 4px;
}

.movie-list .list li .genre {
	color: #999;
	font-size: 12px;
}

.movie-list .load-more {
	background: #EEE;
	padding: 15px;
	margin: 20px;
	border-radius: 5px;
	text-align: center;
	color: #666;
	display: block;
}

.movie-list .load-more:hover {
	background: #DDD;
}

.movie-list .load-more .fa {
	margin-left: 10px;
}

.location-container {
	display: flex;
	align-items: center;
	padding: 0 25px;
	height: 65px;
}

.location-container label {
	color: #FFF;
	font-size: 14px;
	margin-right: 10px;
}

.location-container select {
	background: #3AB0FF;
	color: #FFF;
	padding: 10px;
	border: none;
	border-radius: 5px;
	font-size: 14px;
}

.location-container select:focus {
	outline: none;
	background: #279CEB;
}

@media only screen and (max-width: 1220px) {
	.main .top-bar .top-menu .menu-icon {
		display: block;
	}
}
</style>
</head>
<body>
	<%
	session = request.getSession();
		ArrayList<MovieDetails> lists = (ArrayList<MovieDetails>) session.getAttribute("list");
	%>
	<div class="main" role="main">
		<div class="top-bar">
			<div class="profile-box">
				
			</div>
			<ul class="top-menu">
				<li><a href="#">Home</a></li>
				<li><a href="seat.jsp">Book</a></li>
				<li class="active"><a href="#">Movies</a></li>
				<li><a href="contactUs.jsp">ContactUs</a></li>

				<div class="location-container">
					<label for="location">Location:</label> <select id="location"
						name="location" required>
						<option value="" disabled selected>Select your location</option>
						<option value="Madurai">Madurai</option>
						<option value="Chennai">Chennai</option>
						<option value="Coimbatore">Coimbatore</option>
						<option value="Tiruchirappalli">Tiruchirappalli</option>
						<option value="Salem">Salem</option>
						<option value="Tirunelveli">Tirunelveli</option>
						<option value="Thanjavur">Thanjavur</option>
						<option value="Vellore">Vellore</option>
						<option value="Kanyakumari">Kanyakumari</option>
					</select>
					<img alt="" src="LOGO.png">
			</ul>
		</div>
	</div>
	<div class="featured-movie">
		<img class="cover" src="picture/indian2.jpg" alt="Indian 2" />
		
		<div class="bottom-bar">
			<div class="title-container">
				<span class="fa fa-play-circle"></span> <b>TicketTrick</b>
			</div>
			<div class="right">
				<div class="stars">
					<span class="fa fa-star"></span> <span class="fa fa-star"></span> <span
						class="fa fa-star"></span> <span class="fa fa-star"></span> <span
						class="fa fa-star-o"></span>
				</div>
				<div class="share">
					<span class="fa fa-share"></span> Share
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="container">
		<div class="movie-list">
			<ul class="list">
				<%
				for (MovieDetails list : lists) {
				%>
				<li><a href="shows.jsp"> <img
						src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(list.getImageUrl())%>"
						alt="movie"/>
						<p class="title"><%=list.getTitle()%></p>
						<p class="genre"><%=list.getGenre()%></p>
				</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
	</div>
</body>
</html>
