package com.twu.biblioteca;

class Library {

    private final Book[] books;
    private final Movie[] movies;

    public Library(Book[] books, Movie[] movies) {
        this.books = books;
        this.movies = movies;
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

    private boolean checkOut(int id, Artifact[] artifacts) {
        Artifact artifact = search(id, artifacts);
        if (artifact != null && artifact.getStatus() == Artifact.Status.AVAILABLE) {
            artifact.checkOut();
            return true;
        }
        return false;
    }

    private boolean checkIn(int id, Artifact[] artifacts) {
        Artifact artifact = search(id, artifacts);
        if (artifact != null && artifact.getStatus() == Artifact.Status.BORROWED) {
            artifact.checkIn();
            return true;
        }
        return false;
    }

    public boolean checkoutBook(int book_id) {
        return checkOut(book_id, books);
    }

    public boolean returnBook(int book_id) {
        return checkIn(book_id, books);
    }

    public boolean checkoutMovie(int movie_id) {
        return checkOut(movie_id, movies);
    }

    public boolean returnMovie(int movie_id) {
        return checkIn(movie_id, movies);
    }
}
