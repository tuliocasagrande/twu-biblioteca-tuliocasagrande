package com.twu.biblioteca;

class Library {

    private final Book[] books;
    private final Movie[] movies;
    private final User[] users;

    public Library(Book[] books, Movie[] movies, User[] users) {
        this.books = books;
        this.movies = movies;
        this.users = users;
    }

    public Book[] getBooks() {
        return books;
    }

    public Movie[] getMovies() {
        return movies;
    }

    private Artifact search(int id, Artifact[] artifacts) {
        for (Artifact artifact : artifacts) {
            if (artifact.getId() == id) {
                return artifact;
            }
        }
        return null;
    }

    private boolean checkOut(int id, Artifact[] artifacts, User borrower) {
        Artifact artifact = search(id, artifacts);
        return artifact != null && artifact.checkOut(borrower);
    }

    private boolean checkIn(int id, Artifact[] artifacts, User borrower) {
        Artifact artifact = search(id, artifacts);
        return artifact != null && artifact.checkIn(borrower);
    }

    public boolean checkoutBook(int book_id, User borrower) {
        return checkOut(book_id, books, borrower);
    }

    public boolean returnBook(int book_id, User borrower) {
        return checkIn(book_id, books, borrower);
    }

    public boolean checkoutMovie(int movie_id, User borrower) {
        return checkOut(movie_id, movies, borrower);
    }

    public boolean returnMovie(int movie_id, User borrower) {
        return checkIn(movie_id, movies, borrower);
    }

    // DANGER!!! Missing cryptography!!!
    public User authenticate(String libraryNumber, String password) {
        for (User u : users) {
            if (libraryNumber.equals(u.getLibraryNumber()) && password.equals(u.getPassword())) {
                return u;
            }
        }
        return null;
    }
}
