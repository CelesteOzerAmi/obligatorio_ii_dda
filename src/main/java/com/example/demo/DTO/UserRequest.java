package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
    @NotBlank(message = "Ingrese un nombre")
    String name,
    @NotBlank(message = "Ingrese un email correcto")
    @Email(message = "Ingrese un email correcto")
    String email
) {}
