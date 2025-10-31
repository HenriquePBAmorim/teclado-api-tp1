package br.unitins.topicos1.teclado.dto;

import java.time.LocalDate;
import br.unitins.topicos1.teclado.model.Estoque;

public record EstoqueDTOResponse(
    Long id,
    Integer quantidade,
    LocalDate dataAtualizacao
) {
    public static EstoqueDTOResponse valueOf(Estoque e) {
        return new EstoqueDTOResponse(
            e.getId(),
            e.getQuantidade(),
            e.getDataAtualizacao()
        );
    }
}