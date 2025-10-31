package br.unitins.topicos1.teclado.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum TipoAcessorio {
    SUPORTE(1, "Suporte"),
    DESCANSO_PUNHO(2, "Descanso de Punho"),
    CABO(3, "Cabo"),
    CAPA(4, "Capa"),
    CASE(5, "Case"),
    KIT_LIMPEZA(6, "Kit de Limpeza");

    @JsonProperty("id")
    public final Integer ID;
    @JsonProperty("label")
    public final String LABEL;

    TipoAcessorio(Integer id, String label) {
        this.ID = id;
        this.LABEL = label;
    }

    public static TipoAcessorio valueOf(Integer id) {
        if (id == null) return null;
        for (TipoAcessorio tipo : TipoAcessorio.values()) {
            if (id.equals(tipo.ID))
                return tipo;
        }
        return null;
    }
}