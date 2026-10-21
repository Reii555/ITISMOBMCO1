package com.itismob.s02.salazar.allenaedelweiss.myapplication;

import java.util.ArrayList;
import java.util.List;

public class BookDataGenerator {

    public static List<Book> getRecentActivityBooks() {
        List<Book> books = new ArrayList<>();

        books.add(new Book(
                "The Maze Runner",
                "James Dashner",
                "Thomas wakes up with no memory in the Glade, a walled community of teenage boys surrounded by a shifting, monster-filled maze.",
                R.drawable.the_maze_runner
        ));

        books.add(new Book(
                "The Scorch Trials",
                "James Dashner",
                "Thomas and the Gladers must cross a brutal, virus-ravaged desert wasteland filled with murderous infected humans to complete WICKED’s next deadly trial.",
                R.drawable.the_scorch_trials
        ));

        books.add(new Book(
                "Omniscient Reader's Viewpoint, Vol. 1 (English)",
                "SingNsong",
                "Omniscient Reader's Viewpoint, Vol. 1 follows ordinary office worker Dokja Kim, whose real world instantly transforms into the exact setting of a violent, apocalyptic survival web novel he spent ten years reading.",
                R.drawable.orv1
        ));

        return books;
    }

    public static List<Book> getContinueReadingBooks() {
        List<Book> books = new ArrayList<>();

        books.add(new Book(
                "The Hunger Games",
                "Suzanne Collins",
                "Katniss Everdeen volunteers for a televised, fight-to-the-death tournament to save her sister and must use her hunting skills to survive against twenty-three other teenagers.",
                R.drawable.the_hunger_games
        ));

        books.add(new Book(
                "The Death Cure",
                "James Dashner",
                "Thomas leads a final rebellion against WICKED to save his immune friends and escape the collapsing civilization before the organization can forcefully extract a cure from his brain.",
                R.drawable.the_death_cure
        ));

        books.add(new Book(
                "The Kill Order",
                "James Dashner",
                "Thirteen years before the Maze, survivors Mark and Trina fight to stay alive after solar flares ruin the Earth and a government-engineered virus triggers the apocalypse.",
                R.drawable.the_kill_order
        ));

        return books;
    }
}