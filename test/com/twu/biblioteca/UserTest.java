package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("123-1234", "weak_password", User.Type.CUSTOMER);
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
    public void userHasType() throws Exception {
        assertEquals(User.Type.CUSTOMER, user.getType());
    }
}
