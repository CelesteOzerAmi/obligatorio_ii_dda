package com.example.demo.DTO;

import com.example.demo.Entity.CategoryEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeriesRequest(
    @NotBlank(message = "Ingrese el título")
    String name,
    @NotBlank(message = "Ingrese descripción")
    String description,
    CategoryEntity category,
    @Min(1)
    Integer seasons,
    @Min(1)
    Integer chapters,
    @NotBlank(message = "Ingrese el año")
    String year,
    @NotNull
    @Min(1)
    double purchasePrice,
    @Min(1)
    double rentPrice,
    boolean premiumExclusive
) {}
