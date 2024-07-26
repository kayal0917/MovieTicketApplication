<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Recommended Movies</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgba(0, 0, 0, 0.4);
	opacity: 0; /* Initially hidden */
	transition: opacity 1s ease;
}
.modal-content {
	background-color: #fefefe;
	margin: 7% auto;
/* 	padding: 20px;
 */	border: 1px solid #888;
	width: 76%;
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

.container {
	max-width: 482px;
	margin: 0 auto;
	padding: 1px;
}

.movie-list {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
}

.movie-item {
	flex: 0 0 calc(33.33% - 10px);
	margin-bottom: 20px;
}

.movie-image {
	width: 100%;
	height: auto;
	border-radius: 6.3rem;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body onload="openModal()">

	<div id="locationModal" class="modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="container mt-4">
						<h1 class="text-center mb-4">Top Cities</h1>
						<div class="row mb-3">
							<div class="col-md-6">
								<a href="home.jsp">
								
								<button type="button"
									class="btn btn-primary btn-block city-button">Chennai</button>
								
								</a>
							</div>
							<div class="col-md-6">
								<button type="button"
									class="btn btn-primary btn-block city-button">Coimbatore</button>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-md-6">
								<button type="button"
									class="btn btn-primary btn-block city-button">Madurai</button>
							</div>
							<div class="col-md-6">
								<button type="button"
									class="btn btn-primary btn-block city-button">Tiruchirappalli</button>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-md-6">
								<button type="button"
									class="btn btn-primary btn-block city-button">Salem</button>
							</div>
							<div class="col-md-6">
								<button type="button"
									class="btn btn-primary btn-block city-button">Tirunelveli</button>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-md-6">
								<button type="button"
									class="btn btn-primary btn-block city-button">Thanjavur</button>
							</div>
							<div class="col-md-6">
								<button type="button"
									class="btn btn-primary btn-block city-button">Erode</button>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-md-6">
								<button type="button"
									class="btn btn-primary btn-block city-button">Vellore</button>
							</div>
							<div class="col-md-6">
								<button type="button"
									class="btn btn-primary btn-block city-button">Kanyakumari</button>
							</div>
						</div>
					</div>
					<script>
						// Function to submit the form
						/* function submitForm() {
							console.log("hii");
						    var selectedLocation = document.getElementById("location").value;
						    var form = document.getElementById("locationForm");
						    if (selectedLocation === "thoothukudi") {
						        form.action = "index1.jsp"; // Redirect to theaters in Thoothukudi
						    } else if (selectedLocation === "ranipet") {
						        form.action = "IndexRanipet.jsp"; // Redirect to theaters in Ranipet
						    } else if (selectedLocation === "nagercoil") {
						        form.action = "IndexNagarcovil.jsp"; // Redirect to theaters in Nagercoil
						    }
						    form.submit();
						    closeModal(); // Close the modal after submitting the form
						} */
					</script>


				</div>
			</div>
		</div>

		<!-- Your existing HTML content -->
		<!--  <div class="container">
    <h3 class="mt-5">Recommended Movies</h3>
    <div class="movie-list">
        <div class="movie-item">
            <a href="brama.jsp"><img class="movie-image" src="picture/brama1.jpg" alt="brama"></a>
        </div>
        <div class="movie-item">
            <a href="minusone.jsp"><img class="movie-image" src="picture/godi1.jpg" alt="godzi"></a>
        </div>
        <div class="movie-item">
            <a href="oppen.jsp"><img class="movie-image" src="picture/oppen3.jpg" alt="oppen">
        </div>
    </div>
</div> -->

		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		<script>
			// Get the modal
			var modal = document.getElementById("locationModal");

			// Function to open the modal
			function openModal() {
				modal.style.display = "block";
				setTimeout(function() {
					modal.style.opacity = "1";
				}, 100);
			}

			// Function to close the modal
			function closeModal() {
				modal.style.opacity = "0";
				setTimeout(function() {
					modal.style.display = "none";
				}, 300);
			}

			// Function to submit the form
		</script>
</body>
</html>
