package com.twu.biblioteca;

public class Book {
    private final int id;
    private final String author;
    private final String title;
    private final int year;
    private Status status;

    public Book(int id, String author, String title, int year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public Status getStatus() {
        return status;
    }

    public void checkoutBook() {
        this.status = Status.BORROWED;
    }

    public void returnBook() {
        this.status = Status.AVAILABLE;
    }

    public enum Status {AVAILABLE, BORROWED}
}
