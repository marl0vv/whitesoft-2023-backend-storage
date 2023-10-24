package com.marl0vv.study;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

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
        int nextId = 1;
        for (Entry entry : entries) {
            if (entry.id() == 0 || storage.containsKey(entry.id())) {
                int newId = generateUniqueID(storage, nextId);
                Entry updatedEntry = entry.changeId(newId);
                storage.put(nextId, updatedEntry);
                nextId = newId + 1;
            } else if (!storage.containsKey(entry.id())) {
                storage.put(entry.id(), entry);
            }
        }
    }
    public static Map<Integer, Entry> readFromFile(String filePath) {
        Map<Integer, Entry> storage = new HashMap<>();
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            Type listType = new TypeToken<List<Entry>>() {}.getType();
            List<Entry> entries = gson.fromJson(reader, listType);

            addEntriesToStorage(entries, storage);
            return storage;
        } catch (IOException exception) {
            System.err.println("An error occurred while reading the file: " + exception.getMessage());
            return new HashMap<>();
        }
    }
}
