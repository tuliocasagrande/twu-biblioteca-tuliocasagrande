package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;
    private User librarian;

    @Before
    public void setUp() throws Exception {
        user = new User("123-1234", "weak_password", User.Type.CUSTOMER);
        librarian = new User("121-1212", "1234", User.Type.LIBRARIAN);
    }

    @Test
    public void userHasLibraryNumber() throws Exception {
        assertEquals("123-1234", user.getLibraryNumber());
    }

    @Test
    public void userHasPassword() throws Exception {
        assertEquals("weak_password", user.getPassword());
    }

    @Test
    public void usersHaveAccessLevels() throws Exception {
        assertNotEquals(user.isLibrarian(), librarian.isLibrarian());
    }
}
