package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArtifactClass {

    private Artifact artifact;
    private User customer;
    private User librarian;

    @Before
    public void setUp() throws Exception {
        artifact = new Artifact(1);
        customer = new User("123-1234", "weak_password", "John", "john@email.com", "9999-9999", User.Type.CUSTOMER);
        librarian = new User("121-1212", "1234", "Jane", "jane@email.com", "9999-9999", User.Type.LIBRARIAN);
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
    public void anAvailableArtifactCanBeBorrowed() throws Exception {
        assertTrue(artifact.isAvailable());
        assertTrue(artifact.checkOut(customer));
        assertFalse(artifact.isAvailable());
    }

    @Test
    public void aBorrowedArtifactCanBeReturned() throws Exception {
        artifact.checkOut(customer);
        assertFalse(artifact.isAvailable());
        assertTrue(artifact.checkIn(customer));
        assertTrue(artifact.isAvailable());
    }

    @Test
    public void anAvailableArtifactCannotBeReturned() throws Exception {
        assertTrue(artifact.isAvailable());
        assertFalse(artifact.checkIn(customer));
    }

    @Test
    public void aBorrowedArtifactCannotBeCheckedOut() throws Exception {
        artifact.checkOut(customer);
        assertFalse(artifact.isAvailable());
        assertFalse(artifact.checkOut(customer));
    }

    @Test
    public void aBorrowedArtifactShouldHaveABorrower() throws Exception {
        artifact.checkOut(customer);
        assertNotNull(artifact.getBorrower());
    }

    @Test
    public void aBorrowedArtifactCanBeReturnedByTheBorrower() throws Exception {
        artifact.checkOut(customer);
        assertTrue(artifact.checkIn(customer));
    }

    @Test
    public void aBorrowedArtifactCanBeReturnedByALibrarian() throws Exception {
        artifact.checkOut(customer);
        assertTrue(artifact.checkIn(librarian));
    }

    @Test
    public void aBorrowedArtifactCannotBeReturnedByARandomCustomer() throws Exception {
        artifact.checkOut(customer);
        User anotherCustomer = new User("123-2222", "1234", "Paul", "paul@email.com", "9999-0000", User.Type.CUSTOMER);
        assertFalse(artifact.checkIn(anotherCustomer));
    }
}
