package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import java.util.ArrayList;
import java.util.List;

public class ListDataGenerator {

    public static List<UserList> getPopularLists() {
        List<UserList> userLists = new ArrayList<>();

        List<Book> books1 = BookDataGenerator.getRecentActivityBooks();
        userLists.add(new UserList(
                "Must-Read Dystopian & Sci-Fi Masterpieces",
                "davidehrlich",
                R.drawable.otherpfp,
                "THE ULTIMATE COLLECTION OF GRIPPING DYSTOPIAN NOVELS",
                "(compiled from WormBoxd top community choices)",
                books1
        ));

        List<Book> books2 = BookDataGenerator.getContinueReadingBooks();
        userLists.add(new UserList(
                "Essential YA Action & Survival Series",
                "sarah_reads",
                R.drawable.otherpfp,
                "BEST HIGH-STAKES SURVIVAL AND ACTION ADVENTURES",
                "(thanks to the book club community)",
                books2
        ));

        return userLists;
    }

    public static List<UserList> getFriendsLists() {
        List<UserList> userLists = new ArrayList<>();

        List<Book> books1 = BookDataGenerator.getContinueReadingBooks();
        userLists.add(new UserList(
                "Alex's Top Rated Reads of 2024",
                "alex_m",
                R.drawable.otherpfp,
                "BOOKS THAT KEPT ME UP ALL NIGHT THIS YEAR",
                "Updated yesterday",
                books1
        ));

        List<Book> books2 = BookDataGenerator.getRecentActivityBooks();
        userLists.add(new UserList(
                "Page Turners You Can't Put Down",
                "elena_b",
                R.drawable.otherpfp,
                "FAST-PACED THRILLERS AND SCI-FI RELEASES",
                "Curated by Elena",
                books2
        ));

        return userLists;
    }
}
