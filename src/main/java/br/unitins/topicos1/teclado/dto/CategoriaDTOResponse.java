package br.unitins.topicos1.teclado.dto;

import br.unitins.topicos1.teclado.model.Categoria;

public record CategoriaDTOResponse(
    Long id,
    String nome,
    String descricao
) {
    public static CategoriaDTOResponse valueOf(Categoria c) {
        return new CategoriaDTOResponse(
            c.getId(),
            c.getNome(),
            c.getDescricao()
        );
    }
}