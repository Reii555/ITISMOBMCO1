package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import java.util.List;

public class UserList {
    private String title;
    private String username;
    private int avatarResId;
    private String description;
    private String credit;
    private List<Book> books;

    public UserList(String title, String username, int avatarResId, String description, String credit, List<Book> books) {
        this.title = title;
        this.username = username;
        this.avatarResId = avatarResId;
        this.description = description;
        this.credit = credit;
        this.books = books;
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
}
