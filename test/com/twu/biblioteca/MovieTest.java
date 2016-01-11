package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    private Movie movie;

    @Before
    public void setUp() throws Exception {
        movie = new Movie(1, "The Imitation Game", "Morten Tyldum", 2014, 8);

    }

    @Test
    public void movieHasTitle() throws Exception {
        assertEquals("The Imitation Game", movie.getTitle());
    }

    @Test
    public void movieHasAuthor() throws Exception {
        assertEquals("Morten Tyldum", movie.getDirector());
    }

    @Test
    public void movieHasYear() throws Exception {
        assertEquals(2014, movie.getYear());
    }

    @Test
    public void movieHasRating() throws Exception {
        assertEquals(8, movie.getRating());
    }

    @Test
    public void movieHasId() throws Exception {
        assertEquals(1, movie.getId());
    }
}
