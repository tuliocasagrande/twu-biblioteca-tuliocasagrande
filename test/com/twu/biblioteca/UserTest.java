package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;
    private User librarian;

    @Before
    public void setUp() throws Exception {
        user = new User("123-1234", "weak_password", "John", "john@email.com", "9999-9999" ,User.Type.CUSTOMER);
        librarian = new User("121-1212", "1234", "Jane", "jane@email.com", "9999-9999", User.Type.LIBRARIAN);
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

    @Test
    public void userHasName() throws Exception {
        assertEquals("John", user.getName());
    }

    @Test
    public void userHasEmail() throws Exception {
        assertEquals("john@email.com", user.getEmail());
    }

    @Test
    public void userHasPhoneNumber() throws Exception {
        assertEquals("9999-9999", user.getPhoneNumber());
    }
}
