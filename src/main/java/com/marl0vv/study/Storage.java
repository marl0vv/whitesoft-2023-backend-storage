package com.marl0vv.study;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
public class Storage {
    private Map<Integer, Entry> storage;

    private int nextId = 0;

    public void initializeStorage(String filePath) {
        this.storage = Input.readFromFile(filePath);
    }

    public Entry findByIdOrNull(int id) {
        return storage.get(id);
    }

    public List<Entry> findByName(String name) {
        List<Entry> matchingEntries = new ArrayList<>();
        for (Entry entry : storage.values()) {
            if (entry.getName().toLowerCase().contains(name.toLowerCase())) {
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

    public Entry create(Entry entry) {
        storage.put(entry.getId(), entry);
        return storage.get(entry.getId());
    }

    public void update(Entry newEntry, int id){
        Entry entryToChange = storage.get(id);

        entryToChange.setName(newEntry.getName());
        entryToChange.setDescription(newEntry.getDescription());
        entryToChange.setLink(newEntry.getLink());

        storage.put(id, entryToChange);
    }

    public void delete(Entry entry){
        storage.remove(entry.getId());
    }
    public int getNextId() {
        return ++nextId;
    }
}
