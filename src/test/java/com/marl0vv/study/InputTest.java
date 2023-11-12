package com.marl0vv.study;

import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InputTest {
    @Test
    void testWrittenDataIsNotEmpty() {
        //Arrange
        Map<Integer, Entry> storage = Input.readFromFile("./src/test/resources/inputData.json");

        //Assert
        assertFalse(storage.isEmpty());
    }

    @Test
    public void testReadFromFileInvalidFile() {
        //Arrange
        Map<Integer, Entry> storage = Input.readFromFile("nonExistentFile.json");

        //Assert
        assertTrue(storage.isEmpty());
    }

    @Test
    public void testReadFromFileValidFile() {
        //Arrange
        Map<Integer, Entry> storage = Input.readFromFile("./src/test/resources/inputData.json");
        Entry expectedEntry = new Entry(1, "The Shape of Punk To Come", "Hardcore punk", "https://en.wikipedia.org/wiki/The_Shape_of_Punk_to_Come");

        //Act
        Entry actualEntry = storage.get(1);

        //Assert
        assertEquals(expectedEntry, actualEntry);
    }
}
