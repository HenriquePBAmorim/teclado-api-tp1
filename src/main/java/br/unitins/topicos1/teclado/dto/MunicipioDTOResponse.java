package br.unitins.topicos1.teclado.dto;

import br.unitins.topicos1.teclado.model.Municipio;


public record MunicipioDTOResponse(
    Long id,
    String nome,
    EstadoDTOResponse estado) {  
    
    public static MunicipioDTOResponse valueOf(Municipio municipio) {
        return new MunicipioDTOResponse(
            municipio.getId(),
            municipio.getNome(),
            EstadoDTOResponse.valueOf(municipio.getEstado())
        );
    }
}
