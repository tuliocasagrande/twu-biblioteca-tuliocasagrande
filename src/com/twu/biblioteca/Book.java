package com.twu.biblioteca;

public class Book extends Artifact {
    private final String author;
    private final String title;
    private final int year;

    public Book(int id, String author, String title, int year) {
        super(id);
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

}
