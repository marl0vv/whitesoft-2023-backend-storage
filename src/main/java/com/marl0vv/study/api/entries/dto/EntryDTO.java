package com.marl0vv.study.api.entries.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryDTO {
    private int id;
    private String name;
    private String description;
    private String link;
}
