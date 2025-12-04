package com.example.demo.DTO;

import jakarta.validation.constraints.NotNull;

public record RentRequest (
    @NotNull(message = "Debe ingresar un id de usuario")
    Integer userId
){}

