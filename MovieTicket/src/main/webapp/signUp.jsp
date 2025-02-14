<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
     session = request.getSession(false);
    if (session != null && session.getAttribute("username") != null) {
        response.sendRedirect("demo.jsp"); // Redirect if already logged in
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign Up Page</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background-image: url("picture/popcorn.jpg");
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .signup-form {
            width: 300px;
            padding: 50px;
            background-color: rgba(79, 74, 74, 0.5);
            box-shadow: #D3D3D3;
            border-radius: 10px;
            color: white;
        }

        .signup-form input[type="text"],
        .signup-form input[type="email"],
        .signup-form input[type="password"],
        .signup-form select {
            width: 100%;
            padding: 12px;
            margin-bottom: 10px;
            margin-top: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }

        .signup-form button {
            width: 100%;
            padding: 10px;
            background-color: black;
            color: #ffffff;
            border: none;
            border-radius: 5px;
        }

        .signin-link {
            text-align: center;
            margin-top: 10px;
        }

        .signup-form label {
            margin-top: 10px;
            display: block;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="signup-form">
    <form action="/signUp" method="post" onsubmit="return validateForm()">
        <h2 style="text-align: center;">
            <span style="color: black;">Welcome to TicketTrick</span>
        </h2>
        <h3 style="text-align: center;">Sign Up</h3>
        
        <label for="userName">Username:</label>
        <input type="text" id="userName" name="userName" placeholder="Username" pattern="^[a-zA-Z]+$" required>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}" required>
        
        <label for="location">Location:</label>
        <select id="location" name="location" required>
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
        
        <button type="submit">Sign Up</button>
    </form>
    <div class="signin-link">
        <p>Already have an account? <a href="signin.jsp">Sign In</a></p>
    </div>
</div>

<script>
    function validateForm() {
        var userName = document.getElementById("userName");
        var email = document.getElementById("email");
        var password = document.getElementById("password");
        var location = document.getElementById("location");

        if (!userName.checkValidity()) {
            alert("Username must be filled out and contain only letters.");
            return false;
        }

        if (!email.checkValidity()) {
            alert("Invalid email address.");
            return false;
        }

        if (!password.checkValidity()) {
            alert("Password must be at least 8 characters long, contain at least one number, one uppercase and lowercase letter, and one special character.");
            return false;
        }

        if (location.value === "") {
            alert("Please select a location.");
            return false;
        }

        return true;
    }
</script>

</body>
</html>
