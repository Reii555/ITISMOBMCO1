package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import java.util.ArrayList;
import java.util.List;

public class ReviewDataGenerator {

    public static List<Review> getRecentReviews() {
        List<Review> reviews = new ArrayList<>();

        reviews.add(new Review(
                "September 25, 2026",
                "The Maze Runner",
                4.8F,
                "BEST DYSTOPIAN FILM EVER. I wish I can watch this again for the first time",
                25314,
                R.drawable.the_maze_runner
        ));

        reviews.add(new Review(
                "September 20, 2026",
                "The Scorch Trials",
                4.9F,
                "mama i'm scared of zombies",
                18250,
                R.drawable.the_scorch_trials
        ));

        reviews.add(new Review(
                "September 5, 2026",
                "The Death Cure",
                4.8F,
                "I love this trilogy so much but I lowk cried",
                7650,
                R.drawable.the_death_cure
        ));

        return reviews;
    }
}
