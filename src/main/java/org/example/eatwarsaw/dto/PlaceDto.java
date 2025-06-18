package org.example.eatwarsaw.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class PlaceDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    private Integer googleRatingsCount;
    private Double googleRating;

    private Integer appRatingsCount;
    private Double appRating;

    private String description;

    @NotBlank
    private String imageUrl;

    private Set<Long> categoryIds;


}
