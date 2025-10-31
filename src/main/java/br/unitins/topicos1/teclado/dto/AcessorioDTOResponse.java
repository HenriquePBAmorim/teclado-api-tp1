package br.unitins.topicos1.teclado.dto;

import br.unitins.topicos1.teclado.model.Acessorio;
import br.unitins.topicos1.teclado.model.TipoAcessorio;

public record AcessorioDTOResponse(
    Long id,
    String nome,
    Double preco,
    String compatibilidade,
    TipoAcessorio tipo,
    String material,
    String cor
) {
    public static AcessorioDTOResponse valueOf(Acessorio a) {
        return new AcessorioDTOResponse(
            a.getId(), 
            a.getNome(), 
            a.getPreco(),
            a.getCompatibilidade(),
            a.getTipo(),
            a.getMaterial(),
            a.getCor()
        );
    }
}