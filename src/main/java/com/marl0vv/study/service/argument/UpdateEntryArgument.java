package com.marl0vv.study.service.argument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEntryArgument {
    private String name;
    private String description;
    private String link;
}
