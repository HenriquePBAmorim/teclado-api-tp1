package br.unitins.topicos1.teclado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MarcaDTO(
    @NotBlank(message = "O nome da marca deve ser informado.")
    @Size(max = 100, message = "O nome da marca não pode exceder 100 caracteres.")
    String nome,
    
    String descricao
) {
}