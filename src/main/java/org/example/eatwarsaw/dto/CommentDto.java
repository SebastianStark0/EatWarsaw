package org.example.eatwarsaw.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDto {
    @NotBlank
    private String author;

    @NotBlank
    private String text;

    @Min(1)
    @Max(5)
    private Integer rating;
}