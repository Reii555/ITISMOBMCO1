package com.itismob.s02.salazar.allenaedelweiss.myapplication;

public class Review {

    private String date;
    private String bookTitle;
    private float rating;
    private String content;
    private int likes;
    private int bookImage;

    public Review(String date, String bookTitle, float rating, String content, int likes, int bookImage) {
        this.date = date;
        this.bookTitle = bookTitle;
        this.rating = rating;
        this.content = content;
        this.likes = likes;
        this.bookImage = bookImage;
    }

    public String getDate() {
        return date;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public float getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public int getBookImage() {
        return bookImage;
    }
}