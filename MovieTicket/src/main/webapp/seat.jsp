<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
    background-color: #f2f2f2;
    font-family: Arial, sans-serif;
}

.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 100px;
}

.row {
    display: flex;
    margin-bottom: 10px;
}

.seat {
    width: 30px;
    height: 30px;
    border: 1px solid #000;
    margin: 5px;
    padding: 2px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}

.seat:hover {
    background-color: rgb(232, 53, 53);
}

.seat.selected {
    background-color: rgb(232, 55, 55);
}

.seat.booked {
    background-color: #575353;
}

button {
    padding: 10px 20px;
    background-color: #f00;
    color: #fff;
    border: none;
    cursor: pointer;
}

button:hover {
    background-color: rgb(204, 49, 49);
}
</style>
</head>
<body>
    <h1 style="text-align: center;">Choose Your seats</h1>
		<form action="book.jsp" method="post">
        <div class="container">
            <%
            char[] rows = {'A', 'B', 'C', 'D', 'E', 'F'};
            for (char row : rows) {
            %>
            <div class="row">
                <%
                for (int i = 1; i <= 20; i++) {
                %>
<div class="seat" id="<%=row%><%=i%>"><%=row%><%=i%></div>
                <%
                }
                %>
            </div>
            <%
            }
            %>
        </div>
        <input type="hidden" id="selectedSeats" name="selectedSeats" value="" />
<input type="hidden" id="selectedSeatsCount" name="selectedSeatsCount" value="0" />

        <button type="submit" style="margin-left: 15px;">Submit</button>
    </form>
    <script>
    const seats = document.querySelectorAll('.seat');
    const selectedSeatsInput = document.getElementById('selectedSeats');
    const selectedSeatsCountInput = document.getElementById('selectedSeatsCount'); // Targeting the input field for count
    let selectedSeatsCount = 0;

    seats.forEach(seat => {
        seat.addEventListener('click', () => {
            if (seat.classList.contains('selected')) {
                seat.classList.remove('selected');
                selectedSeatsCount--;
            } else {
                seat.classList.add('selected');
                selectedSeatsCount++;
            }
            updateSelectedSeats();
        });
    });

    function updateSelectedSeats() {
        const selectedSeats = Array.from(document.querySelectorAll('.seat.selected')).map(seat => seat.id);
        selectedSeatsInput.value = selectedSeats.join(',');
        selectedSeatsCountInput.value = selectedSeatsCount; // Update the count
    }


    </script>
</body>
</html>
