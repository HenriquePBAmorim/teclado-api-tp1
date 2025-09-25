package br.unitins.topicos1.teclado.dto;

import br.unitins.topicos1.teclado.model.Marca;

public record MarcaDTOResponse(
    Long id,
    String nome,
    String descricao) {
    
    public static MarcaDTOResponse valueOf(Marca marca) {
        return new MarcaDTOResponse(
            marca.getId(),
            marca.getNome(),
            marca.getDescricao()
        );
    }
}