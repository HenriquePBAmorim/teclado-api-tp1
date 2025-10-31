package br.unitins.topicos1.teclado.dto;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TecladoDTO(
    @NotBlank(message = "O nome deve ser informado.")
    String nome,
    
    @NotNull(message = "O preço deve ser informado.")
    @Positive(message = "O preço deve ser positivo.")
    Double preco,

    @NotNull(message = "O modelo deve ser informado.")
    Long idModelo,

    @NotNull(message = "O tipo do teclado deve ser informado.")
    Integer idTipo,
    
    String idioma,     
    
    @NotNull(message = "O campo 'comFio' deve ser informado (true ou false).")
    Boolean comFio,
    
    @NotNull(message = "O campo 'iluminacaoRgb' deve ser informado (true ou false).")
    Boolean iluminacaoRgb,
    
    LocalDate dataCadastro,
    
    @NotNull(message = "O switch deve ser informado.")
    Long idSwitch,
    
    @NotNull(message = "O keycap deve ser informado.")
    Long idKeycap,
    
    @NotNull(message = "O estoque deve ser informado.")
    Long idEstoque,
    
    @Size(min = 1, message = "O teclado deve ter ao menos uma categoria.")
    List<Long> idCategorias,
    
    List<Long> idAcessorios
) {}