package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryTest {

    Library library;

    @Before
    public void setUp() throws Exception {
        Book[] books = {new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};
        Movie[] movies = {new Movie(1, "The Imitation Game", "Morten Tyldum", 2014, 8),
                new Movie(2, "The Wolf of Wall Street", "Martin Scorsese", 2013, 8)};

        library = new Library(books, movies);
        library.checkoutBook(2);
        library.checkoutMovie(2);
    }

    @Test
    public void anAvailableBookCanBeCheckedOut() throws Exception {
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.AVAILABLE);
        assertTrue(library.checkoutBook(1));
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.BORROWED);
    }

    @Test
    public void aBorrowedBookCanBeReturned() throws Exception {
        assertTrue(library.getBooks()[1].getStatus() == Book.Status.BORROWED);
        assertTrue(library.returnBook(2));
        assertTrue(library.getBooks()[1].getStatus() == Book.Status.AVAILABLE);
    }

    @Test
    public void anAvailableBookCannotBeReturned() throws Exception {
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.AVAILABLE);
        assertFalse(library.returnBook(1));
        assertTrue(library.getBooks()[0].getStatus() == Book.Status.AVAILABLE);
    }

    @Test
    public void aBorrowedBookCannotBeCheckedOut() throws Exception {
        assertTrue(library.getBooks()[1].getStatus() == Book.Status.BORROWED);
        assertFalse(library.checkoutBook(2));
        assertTrue(library.getBooks()[1].getStatus() == Book.Status.BORROWED);
    }

    @Test
    public void anAvailableMovieCanBeCheckedOut() throws Exception {
        assertTrue(library.getMovies()[0].getStatus() == Movie.Status.AVAILABLE);
        assertTrue(library.checkoutMovie(1));
        assertTrue(library.getMovies()[0].getStatus() == Movie.Status.BORROWED);
    }

    @Test
    public void aBorrowedMovieCanBeReturned() throws Exception {
        assertTrue(library.getMovies()[1].getStatus() == Movie.Status.BORROWED);
        assertTrue(library.returnMovie(2));
        assertTrue(library.getMovies()[1].getStatus() == Movie.Status.AVAILABLE);
    }

    @Test
    public void anAvailableMovieCannotBeReturned() throws Exception {
        assertTrue(library.getMovies()[0].getStatus() == Movie.Status.AVAILABLE);
        assertFalse(library.returnMovie(1));
        assertTrue(library.getMovies()[0].getStatus() == Movie.Status.AVAILABLE);
    }

    @Test
    public void aBorrowedMovieCannotBeCheckedOut() throws Exception {
        assertTrue(library.getMovies()[1].getStatus() == Movie.Status.BORROWED);
        assertFalse(library.checkoutMovie(2));
        assertTrue(library.getMovies()[1].getStatus() == Movie.Status.BORROWED);
    }
}
