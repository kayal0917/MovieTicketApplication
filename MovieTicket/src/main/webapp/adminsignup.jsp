<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	padding: 20px;
	background-color: rgba(79, 74, 74, 0.5);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	border-radius: 10px;
	color: white;
}

.signup-form h2 {
	text-align: center;
	color: black;
}

.signup-form h3 {
	text-align: center;
	margin-bottom: 20px;
}

.signup-form input[type="text"], .signup-form input[type="password"] {
	width: calc(100% - 24px); /* Adjusted for padding */
	padding: 10px;
	margin-bottom: 15px;
	border-radius: 5px;
	border: 1px solid #ddd;
}

.signup-form select {
	width: calc(100% - 24px); /* Adjusted for padding */
	padding: 10px;
	margin-bottom: 15px;
	border-radius: 5px;
	border: 1px solid #ddd;
	background-color: #fff;
	color: #555;
}

.signup-form button {
	width: 100%;
	padding: 10px;
	background-color: black;
	color: #ffffff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.signup-form button:hover {
	background-color: #333;
}

.signin-link {
	text-align: center;
	margin-top: 10px;
}

.signin-link a {
	color: #fff;
}
</style>

</head>
<body>

	<div class="signup-form">
		<form action="adminsignup" method="post" onsubmit="return validateForm()">
			<h2>Welcome to TicketFlick</h2>
			
			<h3>Sign Up</h3>
			<label for="userName">Username:</label> <input type="text"
				id="userName" name="userName" placeholder="Username" pattern="^[a-zA-Z][a-zA-Z0-9_-]{3,19}$
" required><br>
			<br> <label for="password">Password:</label> <input
				type="password" id="password" name="password" placeholder="Password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}"
				required><br>
			<br> <label for="location">Location:</label> <select
				id="location" name="location" required>
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
			</select><br>
			<br> <label for="theater">Theater:</label> <input type="text"
				id="theater" name="theater" placeholder="Theater name"  pattern="^[a-zA-Z]+$"  required><br>
			<br>
			<button type="submit" onclick="validateForm()">Sign Up</button>
		</form>
		
		<div class="signin-link">
			<p>
				Already have an account? <a href="adminSignin.jsp">Sign In</a>
			</p>
		</div>
	</div>
	<script>
        function validateForm() {
            var userName =document.getElementById("userName");
            var password = document.getElementById("password");
            var theatername = document.getElementById("theatername");
            if (!userName.checkValidity()) {
                alert("Username must be filled out");
                return ;
            }
            if (!password.checkValidity()) {
                alert("Password must be at least 6 characters long");
                return ;
            }
            if (!theatername.checkValidity()) {
                alert("theatername must be filled out");
                return ;
            }
            return true;
        }
    </script>
</body>
</html>
