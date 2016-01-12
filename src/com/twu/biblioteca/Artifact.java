package com.twu.biblioteca;

public class Artifact {
    private final int id;
    private Status status;
    private User borrower;

    Artifact(int id) {
        this.status = Status.AVAILABLE;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return status == Status.AVAILABLE;
    }

    public void checkOut(User borrower) {
        this.status = Status.BORROWED;
        this.borrower = borrower;
    }

    public void checkIn() {
        this.status = Status.AVAILABLE;
        this.borrower = null;
    }

    public User getBorrower() {
        return this.borrower;
    }

    private enum Status {AVAILABLE, BORROWED}
}
