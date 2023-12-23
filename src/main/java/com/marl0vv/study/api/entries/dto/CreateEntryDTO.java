package com.marl0vv.study.api.entries.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO for creating an entry")
public class CreateEntryDTO {
    @NotBlank(message = "Name can't be empty")
    private String name;
    private String description;
    private String link;
}
