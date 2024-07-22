package com.chainsys.movieticket.model;

public class Movie {

	String title;
	String description;
    String releaseDate;
    int duration;
    String genre;
    String director;
	String cast;
    String language;
    double rating;
String imageUrl;
    public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}
	public String getTrailerUrl() {
		return trailerUrl;
	}
	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}
	String trailerUrl;

    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}


    public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	
}
