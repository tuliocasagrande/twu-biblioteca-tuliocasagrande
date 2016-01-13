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

    public boolean checkOut(User borrower) {
        if (isAvailable()) {
            this.status = Status.BORROWED;
            this.borrower = borrower;
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIn(User customer) {
        if (!isAvailable() && customer != null &&
                (customer.isLibrarian() || customer.getLibraryNumber().equals(borrower.getLibraryNumber()))) {
            this.status = Status.AVAILABLE;
            this.borrower = null;
            return true;
        } else {
            return false;
        }
    }

    public User getBorrower() {
        return this.borrower;
    }

    private enum Status {AVAILABLE, BORROWED}
}
