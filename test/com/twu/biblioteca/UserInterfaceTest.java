package com.twu.biblioteca;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UserInterfaceTest {

    @Test
    public void welcomeMessageIsPrinted() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        new UserInterface();
        assertEquals("Welcome!!! :D\n", outContent.toString());

        System.setOut(null);
    }
}
