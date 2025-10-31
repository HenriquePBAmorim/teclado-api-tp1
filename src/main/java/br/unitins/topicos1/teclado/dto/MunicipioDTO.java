package br.unitins.topicos1.teclado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MunicipioDTO(
    @NotBlank(message = "O nome do município deve ser informado.")
    String nome,

    @NotNull(message = "O estado deve ser informado.")
    Long idEstado
) {
}