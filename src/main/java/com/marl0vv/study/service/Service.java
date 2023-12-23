package com.marl0vv.study.service;

import com.marl0vv.study.Entry;
import com.marl0vv.study.Storage;
import com.marl0vv.study.exception.NotFoundException;
import com.marl0vv.study.service.argument.CreateEntryArgument;
import com.marl0vv.study.service.argument.UpdateEntryArgument;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Service {
    private Storage storage;

    public Service(Storage storage) {
        this.storage = storage;
    }

    public Entry getEntryById(int id) {
        return Optional.ofNullable(storage.findByIdOrNull(id))
                .orElseThrow(() -> new NotFoundException("Entry not found"));
    }

    public List<Entry> findEntriesByName(String name) {
        return storage.findByName(name);
    }

    public Entry createEntry(CreateEntryArgument argument) {
        int id = storage.getNextId();

        return storage.create(Entry.builder()
                .id(id)
                .name(argument.getName())
                .description(argument.getDescription())
                .link(argument.getLink())
                .build());
    }

    public void updateEntryById(UpdateEntryArgument argument, int id){
        Entry entry = Entry.builder()
                .id(id)
                .name(argument.getName())
                .description(argument.getDescription())
                .link(argument.getLink())
                .build();
        storage.update(entry, id);
    }

    public void deleteEntryById(int id){
        storage.delete(getEntryById(id));
    }
}
