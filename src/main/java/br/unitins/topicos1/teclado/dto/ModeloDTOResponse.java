package br.unitins.topicos1.teclado.dto;

import br.unitins.topicos1.teclado.model.Modelo;

public record ModeloDTOResponse(
    Long id,
    String nome,
    MarcaDTOResponse marca 
) {
    public static ModeloDTOResponse valueOf(Modelo modelo) {
        return new ModeloDTOResponse(
            modelo.getId(),
            modelo.getNome(),
            // Garante que a marca não seja nula
            modelo.getMarca() == null ? null : MarcaDTOResponse.valueOf(modelo.getMarca())
        );
    }
}