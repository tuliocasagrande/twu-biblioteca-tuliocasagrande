package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArtifactClass {

    private Artifact artifact;
    private User customer;

    @Before
    public void setUp() throws Exception {
        artifact = new Artifact(1);
        customer = new User("123-1234", "weak_password", User.Type.CUSTOMER);
    }

    @Test
    public void artifactHasId() throws Exception {
        assertEquals(1, artifact.getId());
    }

    @Test
    public void artifactIsInitializedWithAvailableStatus() throws Exception {
        assertTrue(artifact.isAvailable());
    }

    @Test
    public void artifactCanBeBorrowed() throws Exception {
        artifact.checkOut(customer);
        assertFalse(artifact.isAvailable());
    }

    @Test
    public void artifactCanBeReturned() throws Exception {
        artifact.checkOut(customer);
        artifact.checkIn();
        assertTrue(artifact.isAvailable());
    }

    @Test
    public void aBorrowedArtifactShouldHaveABorrower() throws Exception {
        artifact.checkOut(customer);
        assertNotNull(artifact.getBorrower());
    }
}
