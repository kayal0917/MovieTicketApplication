<!DOCTYPE html>
<html>
<head>
    <title>Add Showtime</title>
</head>
<body>
    <h2>Add Showtime</h2>
    <form action="searchMovie" method="get">
        <label for="movieTitle">Movie Title:</label>
        <input type="text" id="movieTitle" name="movieTitle" required>
        <input type="submit" value="Search Movie">
    </form>

    <%
        Integer movieId = (Integer) request.getAttribute("movieId");
        if (movieId != null) {
    %>
        <form action="addShowtime" method="post">
            <input type="hidden" name="movieId" value="<%= movieId %>">
            <label for="theaterId">Theater ID:</label>
            <input type="number" id="theaterId" name="theaterId" required>
            <br>
            <label for="showDate">Show Date:</label>
            <input type="date" id="showDate" name="showDate" required>
            <br>
            <label for="showTime">Show Time:</label>
            <input type="time" id="showTime" name="showTime" required>
            <br>
            <input type="submit" value="Add Showtime">
        </form>
    <%
        }
    %>
</body>
</html>
