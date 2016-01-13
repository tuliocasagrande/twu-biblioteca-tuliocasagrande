package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class LibraryTest {

    private Library library;

    @Before
    public void setUp() throws Exception {
        Book[] books = {new Book(1, "Kent Beck", "Test Driven Development: By Example", 2002),
                new Book(2, "Martin Fowler", "Refactoring: Improving the Design of Existing Code", 1999)};
        Movie[] movies = {new Movie(1, "The Imitation Game", "Morten Tyldum", 2014, 8),
                new Movie(2, "The Wolf of Wall Street", "Martin Scorsese", 2013, 8)};
        User customer = new User("123-1234", "weak_password", "John", "john@email.com", "9999-9999", User.Type.CUSTOMER);
        User[] users = {customer};

        library = new Library(books, movies, users);
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
