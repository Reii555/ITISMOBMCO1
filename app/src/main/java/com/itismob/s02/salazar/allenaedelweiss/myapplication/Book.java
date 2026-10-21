package com.itismob.s02.salazar.allenaedelweiss.myapplication;

public class Book {
    private String title, author, description;
    private int imageResId;

    public Book(String title, String author, String description,
                int imageResId) {
        this.title = title;
        this.author = author;
        this.description = description;
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

    public int getImageResId() {
        return imageResId;
    }
}