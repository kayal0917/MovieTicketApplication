<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking Confirmation</title>
</head>
<style>
body {
	background-image: url("picture/signin.jpg");
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-size: cover;
	display: flex;
	justify-content: flex-start;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.booking-form {
	margin-left: 64px;
	width: 300px;
	padding: 4px 48px;
	background-color: rgba(79, 74, 74, 0.5);
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	border-radius: 10px;
	color: white;
}

.booking-form h3 {
	margin-bottom: 20px;
}

.form-group {
	margin-bottom: 15px;
}

.form-control {
	width: 100%;
	padding: 8px;
	border: 1px solid #ced4da;
	border-radius: 4px;
}

.btn {
	background-color: black;
	color: white;
	border: none;
	padding: 10px 20px;
	border-radius: 4px;
	cursor: pointer;
}

.btn:hover {
	background-color: #c82333;
}
a {
    display: block;
    width: 100px;
    margin: 20px auto;
    padding: 10px;
    text-align: center;
    background: #dc3545;
    color: white;
    border-radius: 5px;
    text-decoration: none;
    font-size: 16px;
}

a:hover {
    background: #c82333;
}
</style>
<body>
	<div class="booking-form">

		<h3 id="book">Book Your Ticket</h3>
		 <p>Selected Seats: <%= request.getAttribute("selectedSeats") %></p>
        <p>Seat Count: <%= request.getAttribute("selectedSeatsCount") %></p>
		
	<%
		String selectedSeats = request.getParameter("selectedSeats");
		int selectedSeatsCount = Integer.parseInt(request.getParameter("selectedSeatsCount"));
		int amount=selectedSeatsCount*100;
		if (selectedSeats != null && !selectedSeats.isEmpty()) {
			String[] seatIds = selectedSeats.split(",");
			out.println("<p>You have selected " + selectedSeatsCount + " seat(s):</p>");
			out.println("<ul>");
			for (String seatId : seatIds) {
				out.println("<li>" + seatId + "</li>");
			}
			out.println("</ul>");
		} else {
			out.println("<p>No seats were selected.</p>");
		}
		 String userName=(String)session.getAttribute("userName");
		%> 
		<form action="processBooking" method="post">
		<div class="form-group">
				<input type="hidden"
					id="name" name="username" class="form-control"
					value="<%=userName%>" />
			</div>
			<div class="form-group">
				<label for="seat"> seats:</label> <input type="text" id="seat"
					name="selectedSeats" class="form-control" value="<%=selectedSeats%>" />
			</div>
			<div class="form-group">
				<label for="seat"> seat count:</label> <input type="number"
					id="seatCount" name="selectedSeatsCount" class="form-control"
					value="<%=selectedSeatsCount%>" />
			</div>
			<div class="form-group">
				<label for="showDate">Date:</label> <input type="date" id="date"
					name="showDate" class="form-control" min="2024-06-01"
					max="2024-06-31" required />
			</div>
			 <div class="form-group">
            <label for="amount">Total Amount:</label>
            <input type="number" id="amount" name="totalAmount" class="form-control"  />
        </div>
			
			<div class="form-group text-right">
			
			<%-- <a href="payment.jsp=<%= selectedSeats %>&amount=<%= amount %>">Book Ticket</a> --%>
			<button>Book Ticket</button>
 	</form>
	</div>
	</form>
	</div>
</body>
<script>
	window.onload = function() {
		const seatInput = document.getElementById('seat');
		const seatCountInput = document.getElementById('seatCount');

		const selectedSeats = seatInput.value;
		const selectedSeatsCount = seatCountInput.value;
		if (selectedSeats) {
			seatInput.value = selectedSeats;
			seatCountInput.value = selectedSeatsCount;
		}
	};
</script>
<script>
    function calculateAmount() {
        const seatCount = parseInt(document.getElementById('seatCount').value);
        const pricePerTicket = 100; 
        
        const totalAmount = pricePerTicket * seatCount;

        document.getElementById('amount').value = totalAmount;
    }

    window.onload = function() {
        calculateAmount();
        
    };
</script>



</html>
