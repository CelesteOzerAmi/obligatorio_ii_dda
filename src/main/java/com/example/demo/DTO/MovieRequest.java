package com.example.demo.DTO;

import com.example.demo.Entity.CategoryEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovieRequest(
    @NotBlank(message = "Ingrese el título")
    String name,
    @NotBlank(message = "Ingrese descripción")
    String description,
    CategoryEntity category,
    @Positive
    Integer duration,
    @NotBlank(message = "Ingrese el año")
    String year,
    @NotNull
    @Min(1)
    double purchasePrice,
    @Min(1)
    double rentPrice,
    boolean premiumExclusive) {
}
