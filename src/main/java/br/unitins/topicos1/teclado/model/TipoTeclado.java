package br.unitins.topicos1.teclado.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum TipoTeclado {
    MECANICO(1, "Mecânico"),
    MEMBRANA(2, "Membrana"),
    HIBRIDO(3, "Híbrido");

    @JsonProperty("id")
    public final Integer ID;
    @JsonProperty("label")
    public final String LABEL;

    TipoTeclado(Integer id, String label) {
        this.ID = id;
        this.LABEL = label;
    }

    public static TipoTeclado valueOf(Integer id) {
        if (id == null) return null;
        for (TipoTeclado tipo : TipoTeclado.values()) {
            if (id.equals(tipo.ID))
                return tipo;
        }
        return null;
    }
}