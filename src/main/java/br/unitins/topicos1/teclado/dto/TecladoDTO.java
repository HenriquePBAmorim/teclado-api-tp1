package br.unitins.topicos1.teclado.dto;

public record TecladoDTO(
    String nome,
    String modelo,    
    String tipo,
    String idioma,     
    Boolean comFio,
    Boolean iluminacaoRgb,
    Double preco,
    Long idMarca) {
}