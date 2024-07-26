<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .contact-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
        }

        .contact-container h1 {
            margin-bottom: 20px;
        }

        .app-details {
            margin-bottom: 20px;
        }

        .app-details p {
            margin: 5px 0;
        }

        .contact-container form {
            display: flex;
            flex-direction: column;
        }

        .contact-container input,
        .contact-container textarea {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }

        .contact-container textarea {
            resize: vertical;
        }

        .contact-container button {
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #28a745;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }

        .contact-container button:hover {
            background-color: black;
        }
    </style>
</head>
<body>
    <div class="contact-container">
        <h1>Contact Us</h1>
        <div class="app-details">
            <h2>App Details</h2>
            <p><strong>Application Name:</strong> TicketTrick</p>
            <p><strong>Email:</strong> support@tickettrick.com</p>
            <p><strong>Phone:</strong> +1 (123) 456-7890</p>
        </div>
        <form action="submitContact" method="post">
            <input type="text" name="name" placeholder="Your Name" required>
            <input type="email" name="email" placeholder="Your Email" required>
            <textarea name="message" placeholder="Your Message" rows="5" required></textarea>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
