package com.marl0vv.study;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceTest {

    private Storage storage = new Storage();

    @BeforeAll
    void setupStorage() {
        //Arrange
        storage.initializeStorage("./src/test/resources/inputData.json");
    }

    @Test
    void testFindById() {
        //Arrange
        int testId = 3;

        //Act
        Entry actualEntry = storage.findByIdOrNull(testId);

        //Assert
        assertNotNull(actualEntry);
        assertEquals("Southern Air", actualEntry.getName());
    }

    @Test
    void testFindByErrorId() {
        //Arrange
        int testId = Integer.MIN_VALUE;

        //Act
        Entry actualEntry = storage.findByIdOrNull(testId);

        //Assert
        assertNull(actualEntry);
    }

    @Test
    void testFindByName() {
        //Arrange
        String testName = "Shape";

        //Act
        List<Entry> matchingEntries = storage.findByName(testName);

        //Assert
        assertNotNull(matchingEntries);
        assertEquals(2, matchingEntries.size());
        assertTrue(matchingEntries.get(0).getName().toLowerCase().contains(testName.toLowerCase()));
        assertTrue(matchingEntries.get(1).getName().toLowerCase().contains(testName.toLowerCase()));
    }

    @Test
    void testFindByErrorName() {
        //Arrange
        String testName = "eofaRorRfz";

        //Act
        List<Entry> matchingEntries = storage.findByName(testName);

        //Assert
        assertNotNull(matchingEntries);
        assertTrue(matchingEntries.isEmpty());
    }
}