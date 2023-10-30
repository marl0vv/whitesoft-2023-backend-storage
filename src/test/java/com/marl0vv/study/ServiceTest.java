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
        storage.initializeStorage("./src/test/resources/inputData.json");
    }

    @Test
    void testFindById() {
        int testId = 3;
        Entry actualEntry = storage.findByIdOrNull(testId);

        assertNotNull(actualEntry);
        assertEquals("Southern Air", actualEntry.getName());
    }

    @Test
    void testFindByErrorId() {
        int testId = Integer.MIN_VALUE;
        Entry actualEntry = storage.findByIdOrNull(testId);

        assertNull(actualEntry);
    }

    @Test
    void testFindByName() {
        String testName = "Shape";
        List<Entry> matchingEntries = storage.findByName(testName);

        assertNotNull(matchingEntries);
        for (Entry entry : matchingEntries) {
            assertTrue(entry.getName().toLowerCase().contains(testName.toLowerCase()));
        }
    }

    @Test
    void testFindByErrorName() {
        String testName = "eofaRorRfz";
        List<Entry> matchingEntries = storage.findByName(testName);

        assertNotNull(matchingEntries);
        assertTrue(matchingEntries.isEmpty());
    }
}