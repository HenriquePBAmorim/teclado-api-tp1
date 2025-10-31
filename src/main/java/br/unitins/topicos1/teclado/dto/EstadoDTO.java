package br.unitins.topicos1.teclado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EstadoDTO(
    @NotBlank(message = "O nome deve ser informado.")
    String nome,

    @Size(min = 2, max = 2, message = "A sigla deve conter 2 caracteres.")
    @NotNull(message = "A sigla deve ser informada.")
    String sigla,
    
    @NotNull(message="A regiao deve ser informada.")
    Long idRegiao
) {     
}