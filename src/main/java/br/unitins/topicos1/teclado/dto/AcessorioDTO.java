package br.unitins.topicos1.teclado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AcessorioDTO(
    @NotBlank(message = "O nome não pode ser nulo.")
    String nome,
    
    @NotNull(message = "O preço deve ser informado.")
    @Positive(message = "O preço deve ser positivo.")
    Double preco,
    
    String compatibilidade,
    
    @NotNull(message = "O tipo do acessório deve ser informado.")
    Integer idTipoAcessorio,
    
    String material,
    String cor
) {}