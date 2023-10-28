package com.marl0vv.study;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entry{
    private int id;
    private String name;
    private String description;
    private String link;

    public void printEntry() {
        System.out.println("Id: " + id + "; Name: " + name + "; Description: " + description + "; Link: " + link);
    }
}
