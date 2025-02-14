<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Movie</title>
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
    height: 214vh;
    margin: 0;
}

.movie-form {
    width: 400px;
    padding: 20px;
    background-color: rgba(79, 74, 74, 0.5);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    color: white;
}

.movie-form h2 {
    text-align: center;
    color: black;
}

.movie-form label {
    display: block;
    margin-bottom: 10px;
    color: white;
}

.movie-form input[type="text"], .movie-form input[type="date"], .movie-form input[type="number"], .movie-form select {
    width: calc(100% - 24px);
    padding: 10px;
    margin-bottom: 15px;
    border-radius: 5px;
    border: 1px solid #ddd;
}

.movie-form button {
    width: 100%;
    padding: 10px;
    background-color: black;
    color: #ffffff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.movie-form button:hover {
    background-color: #333;
}
</style>

</head>
<body>
<div class="movie-form">
    <form action="/movielist" enctype="multipart/form-data" method="post">
     
        <h2>Add Movie</h2>

        <label for="title">Title:</label>
        <input type="text" id="title" name="title" placeholder="Movie Title" required>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" placeholder="Movie Description">

        <label for="releaseDate">Release Date:</label>
        <input type="date" id="releaseDate" name="releaseDate" required>

        <label for="duration">Duration (minutes):</label>
        <input type="number" id="duration" name="duration" placeholder="Duration in minutes" required>

        <label for="genre">Genre:</label>
        <input type="text" id="genre" name="genre" placeholder="Genre" required>

        <label for="director">Director:</label>
        <input type="text" id="director" name="director" placeholder="Director">

        <label for="cast">Cast:</label>
        <input type="text" id="cast" name="cast" placeholder="Cast">

        <label for="language">Language:</label>
        <input type="text" id="language" name="language" placeholder="Language">
        
        <label for="rating">Rating:</label>
        <input type="number" step="0.1" id="rating" name="rating" placeholder="Rating (e.g., 8.5)" required>

    <label for="imageUrl">Image:</label>
        <input type="file" id="imageUrl" name="imageUrl" accept="image/*" required>

        <label for="trailerUrl">Trailer URL:</label>
        <input type="text" id="trailerUrl" name="trailerUrl" placeholder="Trailer URL">

        <button type="submit">Add Movie</button>
    </form>
</div>
</body>
</html>
