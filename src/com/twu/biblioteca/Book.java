package com.twu.biblioteca;

public class Book {
    private int id;
    private String author;
    private String title;
    private int yearPublished;
    private Status status;

    public enum Status {AVAILABLE, BORROWED}

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
}
