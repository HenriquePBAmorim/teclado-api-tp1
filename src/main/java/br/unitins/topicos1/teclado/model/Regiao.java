package br.unitins.topicos1.teclado.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum Regiao {
    CENTRO_OESTE(1L, "Centro-Oeste"), 
    NORDESTE(2L, "Nordeste"), 
    NORTE(3L, "Norte"), 
    SUDESTE(4L, "Sudeste"), 
    SUL(5L, "Sul");

    @JsonProperty("id")
    public final Long ID;
    @JsonProperty("nome")
    public final String NOME;

    Regiao(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public static Regiao valueOf(Long id) {
        if (id == null) return null;
        for (Regiao regiao : Regiao.values()) {
            if (id.equals(regiao.ID))
                return regiao;
        }
        return null;
    }
}