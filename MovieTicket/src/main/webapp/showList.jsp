<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.chainsys.movieticket.model.ShowTime"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show List</title>
<style>
body {
    font-family: Arial, Helvetica, sans-serif;
    background-color: #f4f4f4;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.show-table {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    max-width: 800px;
    width: 100%;
}

table {
    width: 100%;
    border-collapse: collapse;
}

th, td {
    padding: 8px 12px;
    border: 1px solid #ddd;
    text-align: left;
}

th {
    background-color: #f4f4f4;
}

.add-show-link {
    text-align: center;
    margin-top: 20px;
}

.add-show-link a {
    text-decoration: none;
    color: #007BFF;
    font-size: 16px;
    margin: 10px;
    padding: 10px 20px;
    border: 1px solid #007BFF;
    border-radius: 4px;
    transition: background-color 0.3s ease, color 0.3s ease;
}

.add-show-link a:hover {
    background-color: #007BFF;
    color: #fff;
}
</style>
</head>
<body>
    <div class="show-table">
        <h1>Show Times</h1>
        <table>
            <thead>
                <tr>
                    <th>Show ID</th>
                    <th>Movie Name</th>
                    <th>Theater ID</th>
                    <th>Show Date</th>
                    <th>Show Time</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<ShowTime> showList = (List<ShowTime>) request.getAttribute("showList");
                if (showList != null) {
                    for (ShowTime show : showList) {
                %>
                <tr>
                    <td><%=show.getShowtimeId()%></td>
                    <td><%=show.getMovieName()%></td>
                    <td><%=show.getTheaterId()%></td>
                    <td><%=show.getShowDate()%></td>
                    <td><%=show.getShowTime()%></td>
                    <td>
                        <form action="deleteShow" method="post">
                            <input type="hidden" name="showtime_id" value="<%=show.getShowtimeId()%>"> 
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
                <%
                    }
                }
                %>
            </tbody>
        </table>
    </div>

    <div class="add-show-link">
        <a href="addShow.jsp">Add New Show</a>
        <a href="movie.jsp">Add New Movie</a>
    </div>
</body>
</html>
