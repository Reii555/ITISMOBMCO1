package com.itismob.s02.salazar.allenaedelweiss.myapplication;

public class Review {

    private String date, bookTitle, content;
    private float rating;
    private int likes, bookImage;
    private String friendName;
    private int friendPfp;

    public Review(String date, String bookTitle, float rating, String content, int likes, int bookImage) {
        this(date, bookTitle, rating, content, likes, bookImage, null, 0);
    }

    public Review(String date, String bookTitle, float rating, String content, int likes, int bookImage, String friendName, int friendPfp) {
        this.date = date;
        this.bookTitle = bookTitle;
        this.rating = rating;
        this.content = content;
        this.likes = likes;
        this.bookImage = bookImage;
        this.friendName = friendName;
        this.friendPfp = friendPfp;
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

    public String getFriendName() {
        return friendName;
    }

    public int getFriendPfp() {
        return friendPfp;
    }
}
