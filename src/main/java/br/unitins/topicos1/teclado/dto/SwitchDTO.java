package br.unitins.topicos1.teclado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SwitchDTO(
    @NotBlank(message = "O nome não pode ser nulo.")
    String nome,
    
    String fabricante,
    
    @NotNull(message = "O tipo do switch deve ser informado.")
    Integer idTipoSwitch,
    
    @Positive(message = "A força de atuação deve ser positiva.")
    Double forcaAtuacao
) {}