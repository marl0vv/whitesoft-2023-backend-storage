package com.marl0vv.study.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Schema(description = "Error DTO")
public class ErrorDTO {
    private String errorMessage;
}
