package com.twu.biblioteca;

public class Artifact {
    private final int id;
    private Status status;

    Artifact(int id) {
        this.status = Status.AVAILABLE;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void checkOut() {
        this.status = Status.BORROWED;
    }

    public void checkIn() {
        this.status = Status.AVAILABLE;
    }

    public enum Status {AVAILABLE, BORROWED}
}
