package com.twu.biblioteca;

public class Book {
    private final int id;
    private final String author;
    private final String title;
    private final int yearPublished;
    private Status status;

    public Book(int id, String author, String title, int yearPublished) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.yearPublished = yearPublished;
        this.status = Status.AVAILABLE;
    }

    public int getId() {
        return id;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {AVAILABLE, BORROWED}
}
