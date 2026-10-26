package com.itismob.s02.salazar.allenaedelweiss.myapplication;

public class Book {
    private String title, author, description, genre;
    private int imageResId, releaseYear, popularity;
    private float rating;

    public Book(String title, String author, String description, float rating, int imageResId) {
        this(title, author, description, "General", 2020, 1000, rating, imageResId);
    }

    // Full 8-parameter constructor
    public Book(String title, String author, String description, String genre,
                int releaseYear, int popularity, float rating, int imageResId) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.popularity = popularity;
        this.rating = rating;
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

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear(){
        return releaseYear;
    }

    public int getPopularity(){
        return popularity;
    }

    public float getRating() {
        return rating;
    }

    public int getImageResId() {
        return imageResId;
    }
}