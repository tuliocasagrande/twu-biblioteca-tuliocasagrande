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

    private Book searchBook(int book_id) {
        for (Book b : books) {
            if (b.getId() == book_id) {
                return b;
            }
        }
        return null;
    }

    public boolean checkoutBook(int book_id) {
        Book book = searchBook(book_id);
        if (book != null && book.getStatus() == Book.Status.AVAILABLE) {
            book.checkOut();
            return true;
        }
        return false;
    }

    public boolean returnBook(int book_id) {
        Book book = searchBook(book_id);
        if (book != null && book.getStatus() == Book.Status.BORROWED) {
            book.checkIn();
            return true;
        }
        return false;
    }

    private Movie searchMovie(int movie_id) {
        for (Movie m : movies) {
            if (m.getId() == movie_id) {
                return m;
            }
        }
        return null;
    }

    public boolean checkoutMovie(int movie_id) {
        Movie movie = searchMovie(movie_id);
        if (movie != null && movie.getStatus() == Movie.Status.AVAILABLE) {
            movie.checkOut();
            return true;
        }
        return false;
    }

    public boolean returnMovie(int movie_id) {
        Movie movie = searchMovie(movie_id);
        if (movie != null && movie.getStatus() == Movie.Status.BORROWED) {
            movie.checkIn();
            return true;
        }
        return false;
    }
}
