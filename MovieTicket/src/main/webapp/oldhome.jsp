<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie Booking</title>
    <style>
        /* Basic CSS for header and footer */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        header, footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 10px 0;
        }
        header a {
            color: white;
            text-decoration: none;
            margin: 0 10px;
        }
        header a:hover {
            text-decoration: underline;
        }
        main {
            padding: 20px;
        }
        section {
            margin-bottom: 20px;
        }
        section div {
            background-color: #f0f0f0;
            padding: 3px;
            margin-bottom: 10px;
        }
        button {
            background-color: #4CAF50; /* Green */
            color: white;
            padding: 8px 12px;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .dest__wrap {
            width: 80%;
            max-width: 1200px;
            text-align: center;
            margin: 60px auto 0;
        }
        
        .dest__list {
            width: 100%;
            margin-top: 40px;
            text-align: center;
            overflow: hidden; /* Clear float */
        }
        
        .dest__item {
            width: 30%; /* Approximately one-third of the width */
            margin: 0 1%; /* Adjust spacing between items */
            float: left; /* Float left to create a horizontal layout */
            position: relative;
        }
        
        .abc {
            height: 491px;
            position: relative;
            filter: grayscale(10%);
        }
        
        .dest__item img {
            width: 100%; /* Make the image fill its container */
            height: auto; /* Maintain aspect ratio */
        }
        
        .dest__item-info, .dest__item-infos, .dest__item-infose {
            background-size: cover;
            background-repeat: no-repeat;
            color: #fff;
            display: flex;
            flex-direction: column;
            justify-content: flex-end;
            width: 100%;
            position: absolute;
            bottom: 0;
            left: 0;
            top: 0;
            padding-left: 24px;
            padding-bottom: 20px;
            padding-top: 20px;
        }
     
        .dest__item-text {
            font-size: 16px;
            letter-spacing: 2px;
        }
        
        .dest__item:hover .dest__overlay {
            opacity: 1;
        }
        
        .dest__overlay {
            padding: 0 17px;
            width: 100%;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #14b9d5;
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            opacity: 0;
            transition: linear 0.5s;
        }
        
        .dest__overlay h5 {
            font-size: 20px;
            font-weight: 600;
            letter-spacing: 1px;
            color: #fff;
            margin: 0 0 16px;
        }
        
        .dest__overlay-list {
            list-style: none;
        }
        
        .dest__overlay-link {
            color: #fff;
            text-decoration: none;
            line-height: 1.7;
        }
        
        .dest__overlay-btn {
            padding: 3px 23px;
            background-color: #fff;
            color: #14b9d5;
            margin-top: 24px;
            border-radius: 30px;
            text-decoration: none;
            display: inline-block;
            font-size: 15px;
            transition: linear 0.2s;
        }
        
        .dest__overlay-btn:hover {
            /* animation: ring 1s linear; */
            opacity: 0.95;
            transform: scale(1.03);
        }
        
        @keyframes ring {
            0% {
                transform: rotate(0deg);
            }
            25% {
                transform: rotate(1deg);
            }
            50% {
                transform: rotate(-1deg);
            }
            75% {
                transform: rotate(1deg);
            }
            0% {
                transform: rotate(0deg);
            }
        }

        @media (max-width: 768px) {
            .dest__item {
                width: 45%; 
                margin: 0 2.5%; 
            }
        }

        @media (max-width: 480px) {
            .dest__item {
                width: 100%; 
                margin: 0; 
            }
        }
    </style>
</head>
<body>
<% 
HttpSession s = request.getSession(false);
if (session == null) {
    response.sendRedirect("index.jsp");
    return;
}
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Expires", "0"); 
String userName = (String) s.getAttribute("username");
System.out.print(userName);
%>

    <header>
        <h1>Movie Booking System</h1>
        <nav>
            <a href="signin.jsp">Sign In</a>
            <a href="show.jsp">Booked Tickets</a>
            <a class = "logout" href="LogoutServlet">Logout</a>
            
        </nav>
    </header>

    <section class="dest__wrap" id="destination">
<h2>TICKETFLICK Welcomes you, <%= session.getAttribute("username") %></h2>
            
        <h2 class="title__text">New <span class="--title__text-underline dest-text">Recent movie</span></h2>
        <article class="dest__list">
            <!-- Europe -->
            <div class="dest__item abc dest__europe">
                <div class="dest__item-info" style="background-image: url('picture/brama1.jpg');">
                    <p class="dest__item-text"></p>
                </div>
                <div class="dest__overlay">
                    <h5>Bramayugam</h5>
                    <ul class="dest__overlay-list">
                        <li class="dest__overlay-item">
                            <a href="#" class="dest__overlay-link">Power corruption and human greed</a>
                        </li>
                        <a href="brama.jsp" class="dest__overlay-btn">BOOK TICKETS</a>
                    </ul>
                </div>
            </div>
            <div class="dest__item abc dest__thailandia">
                <div class="dest__item-infos" style="background-image: url('picture/godi1.jpg');">
                </div>
                <div class="dest__overlay">
                    <h5>Godzila minusone</h5>
                    <ul class="dest__overlay-list">
                        <li class="dest__overlay-item">
                            <a href="#" class="dest__overlay-link">Prehistoric reptilian monster</a>
                        </li>
                        <a href="seat.jsp" class="dest__overlay-btn">BOOK TICKETS</a>
                    </ul>
                </div>
            </div>
            <!-- Asia -->
            <div class="dest__item abc dest__asia">
                <div class="dest__item-infose" style="background-image: url('picture/oppen3.jpg');">
                </div>
                <div class="dest__overlay">
                    <h5>Oppenheimer</h5>
                    <ul class="dest__overlay-list">
                        <li class="dest__overlay-item">
                            <a href="#" class="dest__overlay-link">Life of American theoretical physicist J</a>
                        </li>
                        <a href="seat.jsp" class="dest__overlay-btn">BOOK TICKETS</a>
                    </ul>
                </div>
            </div>
            <div class="dest__item abc dest__thailandia">
                <div class="dest__item-infos" style="background-image: url('picture/batman.jpg');">
                </div>
                <div class="dest__overlay">
                    <h5>Batman Begin</h5>
                    <ul class="dest__overlay-list">
                        <li class="dest__overlay-item">
                            <a href="#" class="dest__overlay-link">Fear</a>
                        </li>
                        <a href="seat.jsp" class="dest__overlay-btn">BOOK TICKETS</a>
                    </ul>
                </div>
            </div>
            <div class="dest__item abc dest__thailandia">
                <div class="dest__item-infos" style="background-image: url('picture/fury.jpg');">
                </div>
                <div class="dest__overlay">
                    <h5>How to train dragon</h5>
                    <ul class="dest__overlay-list">
                        <li class="dest__overlay-item">
                            <a href="#" class="dest__overlay-link">The power of collaboration and teamwork</a>
                        </li>
                        <a href="seat.jsp" class="dest__overlay-btn">BOOK TICKETS</a>
                    </ul>
                </div>
            </div>
            <div class="dest__item abc dest__thailandia">
                <div class="dest__item-infos" style="background-image: url('picture/nimona.jpg');">
                </div>
                <div class="dest__overlay">
                    <h5>Nimona</h5>
                    <ul class="dest__overlay-list">
                        <li class="dest__overlay-item">
                            <a href="#" class="dest__overlay-link">Queerness and fluidity of identity </a>
                        </li>
                        <a href="seat.jsp" class="dest__overlay-btn">BOOK TICKETS</a>
                    </ul>
                </div>
            </div>
            <div class="dest__item abc dest__thailandia">
                <div class="dest__item-infos" style="background-image: url('picture/panda.jpg');">
                </div>
                <div class="dest__overlay">
                    <h5>kung fu panda 4</h5>
                    <ul class="dest__overlay-list">
                        <li class="dest__overlay-item">
                            <a href="#" class="dest__overlay-link">Change is good</a>
                        </li>
                        <a href="seat.jsp" class="dest__overlay-btn">BOOK TICKETS</a>
                    </ul>
                </div>
            </div>
            <!-- Asia -->
            <div class="dest__item abc dest__asia">
                <div class="dest__item-infose" style="background-image: url('picture/dune.jpg');">
                </div>
                <div class="dest__overlay">
                    <h5>Dune</h5>
                    <ul class="dest__overlay-list">
                        <li class="dest__overlay-item">
                            <a href="#" class="dest__overlay-link">Power and Suffering</a>
                        </li>
                        <a href="seat.jsp" class="dest__overlay-btn">BOOK TICKETS</a>
                    </ul>
                </div>
            </div>
            <div class="dest__item abc dest__thailandia">
                <div class="dest__item-infos" style="background-image: url('picture/past.jpg');">
                </div>
                <div class="dest__overlay">
                    <h5>PastLive</h5>
                    <ul class="dest__overlay-list">
                        <li class="dest__overlay-item">
                            <a href="#" class="dest__overlay-link">Love and destiny </a>
                        </li>
                        <a href="seat.jsp" class="dest__overlay-btn">BOOK TICKETS</a>
                    </ul>
                </div>
            </div>
        </article>
    </section>

    <footer>
        <p>&copy; 2024 Movie Booking System. All rights reserved.</p>
    </footer>
</body>
</html>
