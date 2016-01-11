package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArtifactClass {

    private Artifact artifact;

    @Before
    public void setUp() throws Exception {
        artifact = new Artifact(1);
    }
    
    @Test
    public void artifactHasId() throws Exception {
        assertEquals(1, artifact.getId());
    }

    @Test
    public void artifactIsInitializedWithAvailableStatus() throws Exception {
        assertEquals(Movie.Status.AVAILABLE, artifact.getStatus());
    }

    @Test
    public void artifactCanBeBorrowed() throws Exception {
        artifact.checkOut();
        assertEquals(Movie.Status.BORROWED, artifact.getStatus());
    }

    @Test
    public void artifactCanBeReturned() throws Exception {
        artifact.checkOut();
        artifact.checkIn();
        assertEquals(Movie.Status.AVAILABLE, artifact.getStatus());
    }
}
