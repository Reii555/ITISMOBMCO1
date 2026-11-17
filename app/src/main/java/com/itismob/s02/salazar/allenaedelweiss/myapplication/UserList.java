package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserList implements Serializable {
    private String title;
    private String username;
    private int avatarResId;
    private String description;
    private String credit;
    private List<Book> books;
    private int likes;
    private List<String> tags;

    public UserList(String title, String username, int avatarResId, String description, String credit, List<Book> books) {
        this(title, username, avatarResId, description, credit, books, 0, new ArrayList<>());
    }

    public UserList(String title, String username, int avatarResId, String description, String credit, List<Book> books, int likes, List<String> tags) {
        this.title = title;
        this.username = username;
        this.avatarResId = avatarResId;
        this.description = description;
        this.credit = credit;
        this.books = books;
        this.likes = likes;
        this.tags = tags != null ? tags : new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public String getDescription() {
        return description;
    }

    public String getCredit() {
        return credit;
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getLikes() {
        return likes;
    }

    public List<String> getTags() {
        return tags;
    }
}
