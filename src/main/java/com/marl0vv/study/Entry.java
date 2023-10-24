package com.marl0vv.study;

public record Entry(int id, String name, String description, String link) {
    public Entry changeId(int newId){
        return new Entry(newId, this.name, this.description, this.link);
    }
}
