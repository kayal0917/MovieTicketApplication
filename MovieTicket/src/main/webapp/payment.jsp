<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Page</title>
    <link rel="stylesheet" href="styles.css">
    <script src="payment.js" defer></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: black;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .payment-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        .payment-container h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        button {
            margin: 0 auto;
            width: 90%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }

        button {
            background-color: #ff003f;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        input:invalid {
            border-color: #dc3545;
        }

        input:invalid:focus {
            box-shadow: 0 0 5px #dc3545;
        }
    </style>
</head>
<body>
    <div class="payment-container">
        <h1>Payment</h1>
        <form id="paymentForm" action="ticket.jsp" method="post">
            <div class="form-group">
                <label for="cardNumber">Card Number:</label>
                <input type="text" id="cardNumber" name="cardNumber" maxlength="16" pattern="\d{16}" required>
            </div>
            <div class="form-group">
                <label for="expiry">Expiry Date:</label>
                <input type="text" id="expiry" name="expiry" placeholder="MM/YY" pattern="(0[1-9]|1[0-2])\/\d{2}" required>
            </div>
            <div class="form-group">
                <label for="cvv">CVV:</label>
                <input type="text" id="cvv" name="cvv" maxlength="3" pattern="\d{3}" required>
            </div>
            <div class="form-group">
                <label for="cardHolderName">Cardholder Name:</label>
                <input type="text" id="cardHolderName" name="cardHolderName" required>
            </div>
            <button type="submit">Pay Now</button>
        </form>
    </div>

    <script>
        document.getElementById('paymentForm').addEventListener('submit', function(event) {
            const cardNumber = document.getElementById('cardNumber').value;
            const expiry = document.getElementById('expiry').value;
            const cvv = document.getElementById('cvv').value;
            const cardHolderName = document.getElementById('cardHolderName').value;

            const cardNumberPattern = /^\d{16}$/;
            const expiryPattern = /^(0[1-9]|1[0-2])\/\d{2}$/;
            const cvvPattern = /^\d{3}$/;

            if (!cardNumberPattern.test(cardNumber)) {
                alert('Please enter a valid 16-digit card number.');
                event.preventDefault();
                return;
            }

            if (!expiryPattern.test(expiry)) {
                alert('Please enter a valid expiry date in MM/YY format.');
                event.preventDefault();
                return;
            }

            if (!cvvPattern.test(cvv)) {
                alert('Please enter a valid 3-digit CVV.');
                event.preventDefault();
                return;
            }

            if (cardHolderName.trim() === '') {
                alert('Please enter the cardholder name.');
                event.preventDefault();
                return;
            }
        });
    </script>
</body>
</html>
