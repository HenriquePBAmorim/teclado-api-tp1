package br.unitins.topicos1.teclado.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record EstoqueDTO(
    @NotNull(message = "A quantidade não pode ser nula.")
    @PositiveOrZero(message = "A quantidade deve ser 0 ou maior.")
    Integer quantidade,
    
    LocalDate dataAtualizacao
) {}