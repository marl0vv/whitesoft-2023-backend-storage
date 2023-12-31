package com.marl0vv.study;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Input {
    private static int generateUniqueID(Map<Integer, Entry> storage, int startingId) {
        int newId = startingId;
        while (storage.containsKey(newId)) {
            newId++;
        }
        return newId;
    }

    //if entry doesn't have id, or it has id that already exists in storage we add it and ensure that it's unique then add it to storage,
    //else we check if entry has id that doesn't exist in storage we just add this entry
    private static void addEntriesToStorage(List<Entry> entries, Map<Integer, Entry> storage) {
        int nextId = 0;
        for (Entry entry : entries) {
            if (entry.getId() == 0 || storage.containsKey(entry.getId())) {
                int newId = generateUniqueID(storage, nextId);
                entry.setId(newId);
                storage.put(newId, entry);
                nextId = newId + 1;
            } else if (!storage.containsKey(entry.getId())) {
                storage.put(entry.getId(), entry);
            }
        }
    }

    public static Map<Integer, Entry> readFromFile(String filePath) {
        Map<Integer, Entry> storage = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Entry> entries = mapper.readValue(new FileReader(filePath), new TypeReference<>() {
            });
            addEntriesToStorage(entries, storage);
            return storage;
        } catch (IOException exception) {
            System.err.println("An error occurred while reading the file: " + exception.getMessage());
            return new HashMap<>();
        }
    }
}
