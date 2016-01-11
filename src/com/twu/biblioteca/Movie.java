package com.twu.biblioteca;

public class Movie extends Artifact {
    private final String title;
    private final String director;
    private final int year;
    private final int rating; // 1-10 or unrated (-1)

    public Movie(int id, String title, String director, int year, int rating) {
        super(id);
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = (rating > 1 && rating < 10) ? rating : -1;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public int getRating() {
        return rating;
    }

}
