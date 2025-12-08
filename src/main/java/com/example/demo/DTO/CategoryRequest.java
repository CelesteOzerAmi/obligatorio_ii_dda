package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
    @NotBlank(message = "Ingrese nombre")
    String name
) {}
