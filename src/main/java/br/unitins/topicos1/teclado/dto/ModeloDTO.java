package br.unitins.topicos1.teclado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ModeloDTO(
    @NotBlank(message = "O nome do modelo não pode ser nulo.")
    String nome,
    
    @NotNull(message = "A marca deve ser informada.")
    Long idMarca
) {}