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
        input[type="datetime-local"] {
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
            
            <label for="ShowDate">Show Date:</label>
            <input type="datetime-local" id="ShowDate" name="ShowDate" required>
            
            <label for="ShowTime">Show Time:</label>
            <input type="datetime-local" id="ShowTime" name="ShowTime" required>
            
            <input type="submit" value="Add Show">
        </form>
    </div>
</body>
</html>
