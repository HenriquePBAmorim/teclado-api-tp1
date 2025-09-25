package br.unitins.topicos1.teclado.dto;

import br.unitins.topicos1.teclado.model.Teclado;
import br.unitins.topicos1.teclado.model.TipoTeclado;

public record TecladoDTOResponse(
    Long id,
    String nome,
    String modelo,    
    TipoTeclado tipo,
    String idioma,     
    Boolean comFio,
    Boolean iluminacaoRgb,
    Double preco,
    MarcaDTOResponse marca) {
    
    public static TecladoDTOResponse valueOf(Teclado teclado) {
        return new TecladoDTOResponse(
            teclado.getId(),
            teclado.getNome(),
            teclado.getModelo(),
            teclado.getTipo(),
            teclado.getIdioma(),
            teclado.getComFio(),
            teclado.getIluminacaoRgb(),
            teclado.getPreco(),
            MarcaDTOResponse.valueOf(teclado.getMarca())
        );
    }
}