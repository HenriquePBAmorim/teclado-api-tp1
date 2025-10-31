package br.unitins.topicos1.teclado.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
    @NotBlank(message = "O nome não pode ser nulo.")
    String nome,
    String descricao
) {}