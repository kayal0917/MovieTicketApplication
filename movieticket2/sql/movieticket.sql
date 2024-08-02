CREATE TABLE theaters (
    theater_id INT PRIMARY KEY,
    city_id INT,
    theater_name VARCHAR(255),
    FOREIGN KEY (city_id) REFERENCES Cities(city_id)
);
SELECT * FROM movie.theaters;

CREATE TABLE cities (
    city_id INT PRIMARY KEY,
    city_name VARCHAR(255)
);
SELECT * FROM movie.cities;

CREATE TABLE bookings (
    booking_id INT PRIMARY KEY,
    user_name VARCHAR(255),
    seats VARCHAR(255),
    seatcount INT,
    booking_date DATE,
    total_amount DECIMAL(10, 2)
);
SELECT * FROM movie.bookings;

CREATE TABLE admin (
    id INT PRIMARY KEY,
    user_name VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    delete_user BOOLEAN
);
SELECT * FROM movie.admin;

CREATE TABLE users (
    user_name VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    delete_user BOOLEAN
);
SELECT * FROM movie.users;
