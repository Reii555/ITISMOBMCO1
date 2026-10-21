package com.itismob.s02.salazar.allenaedelweiss.myapplication;

public class Book {
    private String title, author, description, genre;
    private int imageResId, releaseYear, popularity;
    private double rating;

    public Book(String title, String author, String description, int imageResId) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.popularity = popularity;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() { return genre; }

    public int getReleaseYear() { return releaseYear; }

    public double getRating() { return rating; }

    public int getPopularity() { return popularity; }

    public int getImageResId() {
        return imageResId;
    }
}