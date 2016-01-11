package com.twu.biblioteca;

public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;
    private int rating; // 1-10 or unrated (-1)

    public Movie(int id, String title, String director, int year, int rating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        if (rating < 1 || rating > 10) this.rating = -1;
    }

    public int getId() {
        return id;
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
