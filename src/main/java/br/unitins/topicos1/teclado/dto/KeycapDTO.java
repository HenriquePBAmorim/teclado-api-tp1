package br.unitins.topicos1.teclado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record KeycapDTO(
    @NotBlank(message = "O nome não pode ser nulo.")
    String nome,
    
    String material,
    
    @NotNull(message = "O perfil deve ser informado.")
    Integer idPerfilKeycap,
    
    String cor
) {}