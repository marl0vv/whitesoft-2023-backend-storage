package com.marl0vv.study;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Storage {
    private Map<Integer, Entry> storage;

    public Storage(){
    }

    public void initializeStorage(String filePath) {
        this.storage = Input.readFromFile(filePath);
    }
    public Entry findByIdOrNull(int id){
        return storage.get(id);
    }

    public List<Entry> findByName(String name){
        List<Entry> matchingEntries = new ArrayList<>();
        for(Entry entry : storage.values()){
            if (entry.getName().toLowerCase().contains(name)){
                matchingEntries.add(entry);
            }
        }
        return matchingEntries;
    }

}
