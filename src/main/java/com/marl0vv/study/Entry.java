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

    @Override
    public boolean equals(Object object){
        if (this == object){
            return true;
        }
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Entry other = (Entry) object;
        return this.id == other.id
                && this.name.equals(other.name)
                && this.description.equals(other.description)
                && this.link.equals(other.link);
    }
}
