package com.example.demo.DTO;
import jakarta.validation.constraints.NotNull;

public record PurchaseRequest  (
    @NotNull(message = "Debe ingresar un id de usuario")
    Integer userId
){}