package com.marl0vv.study;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entry {
    private int id;
    private String name;
    private String description;
    private String link;

    public void printEntry() {
        System.out.println("Id: " + id + "; Name: " + name + "; Description: " + description + "; Link: " + link);
    }
}
