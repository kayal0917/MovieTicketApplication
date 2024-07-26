<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.chainsys.movieticket.model.Users"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All User Details</title>
<style>
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin: 20px;
    background: linear-gradient(to right, #ffecd2, #fcb69f);
    color: #444;
}

h1 {
    color: #333;
    text-align: center;
    font-size: 2.5em;
    margin-bottom: 20px;
    background: -webkit-linear-gradient(#ee0979, #ff6a00);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
}

form {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

label {
    font-weight: bold;
    margin-right: 10px;
    color: #ee0979;
}

input[type="text"] {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    transition: all 0.3s ease;
}

input[type="text"]:focus {
    border-color: #ff6a00;
    box-shadow: 0 0 8px rgba(255, 106, 0, 0.5);
}

button {
    padding: 10px 20px;
    cursor: pointer;
    background: linear-gradient(to right, #ee0979, #ff6a00);
    color: #fff;
    border: none;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    margin-left: 10px;
    transition: all 0.3s ease;
}

button:hover {
    background: linear-gradient(to right, #ff6a00, #ee0979);
    box-shadow: 0 6px 8px rgba(0,0,0,0.2);
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    background: #fff;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}

th, td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: left;
}

th {
    background: linear-gradient(to right, #ff6a00, #ee0979);
    color: white;
    text-transform: uppercase;
    letter-spacing: 1px;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}

tr:hover {
    background-color: #ffecd2;
}

@media (max-width: 600px) {
    table, thead, tbody, th, td, tr {
        display: block;
    }

    th {
        position: absolute;
        top: -9999px;
        left: -9999px;
    }

    tr {
        border: 1px solid #ccc;
    }

    td {
        border: none;
        border-bottom: 1px solid #ddd;
        position: relative;
        padding-left: 50%;
    }

    td:before {
        position: absolute;
        top: 6px;
        left: 6px;
        width: 45%;
        padding-right: 10px;
        white-space: nowrap;
    }

    td:nth-of-type(1):before { content: "UserName"; }
    td:nth-of-type(2):before { content: "Email"; }
    td:nth-of-type(3):before { content: "Password"; }
    td:nth-of-type(4):before { content: "Location"; }
    td:nth-of-type(5):before { content: "Delete"; }
}

</style>
</head>
<body>
    <form action="/search" method="GET">
        <label for="searchTerm">Search by Name:</label>
        <input type="text" id="searchTerm" name="userName" placeholder="Enter name...">
        <button type="submit">Search</button>
    </form>
    <h1>User Details</h1>
    <table>
        <thead>
            <tr>
                <th>UserName</th>
                <th>Email</th>
                <th>Password</th>
                <th>Location</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
            <%
            List<Users> users = (ArrayList<Users>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (Users user : users) {
            %>
            <tr>
                <td><%=user.getuserName()%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getPassword()%></td>
                <td><%=user.getLocation()%></td>
                <td>
                    <form action="/delete">
                        <input type="hidden" name="userName" value="<%=user.getuserName()%>">
                        <button>Delete</button>
                    </form>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5">No Records found</td>
            </tr>
            <%
            }
            %>
        </tbody>
    </table>
</body>
</html>
