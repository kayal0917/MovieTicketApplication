<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add Show</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
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
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
        }
        input[type="text"],
        input[type="datetime-local"],
        input[type="date"],
        input[type="time"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>Add Show</h1>
        <form action="addShow" method="post">
            <label for="MovieName">Movie Name:</label>
            <input type="text" id="MovieName" name="MovieName" required>
            
            <label for="start">Show Date:</label>
            <input type="date" id="start" name="ShowDate" value="2024-07-10" min="2018-01-01" max="2024-12-31" required>
            
            <label for="appt">Show Time:</label>
            <input type="time" id="appt" name="ShowTime" min="09:00" max="18:00" required>
            
            <input type="submit" value="Add Show">
        </form>
    </div>
</body>
</html>
