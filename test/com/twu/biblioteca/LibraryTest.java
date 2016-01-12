package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryTest {

    private Library library;

    @Before
    public void setUp() throws Exception {
        Book[] books = {new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};
        Movie[] movies = {new Movie(1, "The Imitation Game", "Morten Tyldum", 2014, 8),
                new Movie(2, "The Wolf of Wall Street", "Martin Scorsese", 2013, 8)};
        User[] users = {new User("123-1234", "weak_password", User.Type.CUSTOMER),
                new User("121-1212", "1234", User.Type.LIBRARIAN)};

        library = new Library(books, movies, users);
        library.checkoutBook(2);
        library.checkoutMovie(2);
    }

    @Test
    public void anAvailableBookCanBeCheckedOut() throws Exception {
        assertTrue(library.getBooks()[0].isAvailable());
        assertTrue(library.checkoutBook(1));
        assertFalse(library.getBooks()[0].isAvailable());
    }

    @Test
    public void aBorrowedBookCanBeReturned() throws Exception {
        assertFalse(library.getBooks()[1].isAvailable());
        assertTrue(library.returnBook(2));
        assertTrue(library.getBooks()[1].isAvailable());
    }

    @Test
    public void anAvailableBookCannotBeReturned() throws Exception {
        assertTrue(library.getBooks()[0].isAvailable());
        assertFalse(library.returnBook(1));
        assertTrue(library.getBooks()[0].isAvailable());
    }

    @Test
    public void aBorrowedBookCannotBeCheckedOut() throws Exception {
        assertFalse(library.getBooks()[1].isAvailable());
        assertFalse(library.checkoutBook(2));
        assertFalse(library.getBooks()[1].isAvailable());
    }

    @Test
    public void anAvailableMovieCanBeCheckedOut() throws Exception {
        assertTrue(library.getMovies()[0].isAvailable());
        assertTrue(library.checkoutMovie(1));
        assertFalse(library.getMovies()[0].isAvailable());
    }

    @Test
    public void aBorrowedMovieCanBeReturned() throws Exception {
        assertFalse(library.getMovies()[1].isAvailable());
        assertTrue(library.returnMovie(2));
        assertTrue(library.getMovies()[1].isAvailable());
    }

    @Test
    public void anAvailableMovieCannotBeReturned() throws Exception {
        assertTrue(library.getMovies()[0].isAvailable());
        assertFalse(library.returnMovie(1));
        assertTrue(library.getMovies()[0].isAvailable());
    }

    @Test
    public void aBorrowedMovieCannotBeCheckedOut() throws Exception {
        assertFalse(library.getMovies()[1].isAvailable());
        assertFalse(library.checkoutMovie(2));
        assertFalse(library.getMovies()[1].isAvailable());
    }

    @Test
    public void validCredentialsShouldAuthenticate() throws Exception {
        assertNotNull(library.authenticate("123-1234", "weak_password"));
    }

    @Test
    public void invalidCredentialsShouldNotAuthenticate() throws Exception {
        assertNull(library.authenticate("123-1234", "wrong_password"));
    }
}
