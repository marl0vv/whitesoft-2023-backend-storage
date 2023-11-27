package com.marl0vv.study.service.argument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEntryArgument {
    private String name;
    private String description;
    private String link;
}
