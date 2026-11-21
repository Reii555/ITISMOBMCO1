package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import java.util.ArrayList;
import java.util.Collections;
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
                R.drawable.the_maze_runner,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        reviews.add(new Review(
                "September 20, 2026",
                "The Scorch Trials",
                4.9F,
                "mama i'm scared of zombies",
                18250,
                R.drawable.the_scorch_trials,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        reviews.add(new Review(
                "September 5, 2026",
                "The Death Cure",
                4.8F,
                "I love this trilogy so much but I lowk cried",
                7650,
                R.drawable.the_death_cure,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        return reviews;
    }

    public static List<Review> getFriendsReviews() {
        List<Review> reviews = new ArrayList<>();

        reviews.add(new Review(
                "September 25, 2026",
                "Omniscient Reader's Viewpoint, Vol. 1",
                4.8F,
                "nameet ni bakla yung idol niya na nasa closet pa",
                25314,
                R.drawable.orv1,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        reviews.add(new Review(
                "September 20, 2026",
                "The Maze Runner",
                4.8F,
                "BEST DYSTOPIAN FILM EVER. I wish I can watch this again for the first time!",
                1420,
                R.drawable.the_maze_runner,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        reviews.add(new Review(
                "July 22, 2025",
                "The Hunger Games",
                4.5F,
                "Katniss is such an iconic protagonist. Unputdownable from page 1.",
                890,
                R.drawable.the_hunger_games,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        reviews.add(new Review(
                "September 20, 2026",
                "The Scorch Trials",
                4.9F,
                "mama i'm scared of zombies",
                18250,
                R.drawable.the_scorch_trials,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        reviews.add(new Review(
                "September 5, 2026",
                "The Death Cure",
                4.8F,
                "I love this trilogy so much but I lowk cried",
                7650,
                R.drawable.the_death_cure,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        return reviews;
    }

    public static List<Review> getPopularReviews() {
        List<Review> reviews = new ArrayList<>();

        reviews.add(new Review(
                "September 25, 2026",
                "The Maze Runner",
                4.8F,
                "BEST DYSTOPIAN FILM EVER. I wish I can watch this again for the first time!",
                35314,
                R.drawable.the_maze_runner,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        reviews.add(new Review(
                "September 20, 2026",
                "Omniscient Reader's Viewpoint, Vol. 1",
                4.8F,
                "nameet ni bakla yung idol niya na nasa closet pa",
                25314,
                R.drawable.orv1,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        reviews.add(new Review(
                "September 20, 2026",
                "The Scorch Trials",
                4.9F,
                "mama i'm scared of zombies",
                18250,
                R.drawable.the_scorch_trials,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        reviews.add(new Review(
                "September 5, 2026",
                "The Death Cure",
                4.8F,
                "I love this trilogy so much but I lowk cried",
                7650,
                R.drawable.the_death_cure,
                "deartheodosia",
                R.drawable.otherpfp
        ));

        // Sort by number of likes in descending order (highest likes = most popular)
        Collections.sort(reviews, (r1, r2) -> Integer.compare(r2.getLikes(), r1.getLikes()));

        return reviews;
    }

    public static List<Review> getReviewsForBook(String bookTitle) {
        List<Review> allReviews = new ArrayList<>();
        allReviews.addAll(getFriendsReviews());
        allReviews.addAll(getPopularReviews());
        allReviews.addAll(getRecentReviews());

        List<Review> filtered = new ArrayList<>();
        if (bookTitle != null && !bookTitle.trim().isEmpty()) {
            String query = bookTitle.toLowerCase().replaceAll("\\(.*?\\)", "").trim();

            for (Review review : allReviews) {
                if (review.getBookTitle() != null) {
                    String reviewTitle = review.getBookTitle().toLowerCase().trim();
                    if (reviewTitle.contains(query) || query.contains(reviewTitle)) {
                        boolean isDuplicate = false;
                        for (Review existing : filtered) {
                            if (existing.getContent() != null && existing.getContent().equals(review.getContent())) {
                                isDuplicate = true;
                                break;
                            }
                        }
                        if (!isDuplicate) {
                            filtered.add(review);
                        }
                    }
                }
            }
        }
        return filtered;
    }
}
