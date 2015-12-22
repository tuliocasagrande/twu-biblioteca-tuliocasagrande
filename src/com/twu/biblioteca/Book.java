package com.twu.biblioteca;

public class Book {
    private String author;
    private String title;
    private int yearPublished;
    private Status status;

    public enum Status {AVAILABLE, BORROWED}

    public Book(String author, String title, int yearPublished) {
        this.author = author;
        this.title = title;
        this.yearPublished = yearPublished;
        this.status = Status.AVAILABLE;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public Status getStatus() {
        return status;
    }
}
