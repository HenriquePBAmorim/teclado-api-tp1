package br.unitins.topicos1.teclado.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum TipoSwitch {
    LINEAR(1, "Linear"),
    TACTILE(2, "Tátil"),
    CLICKY(3, "Clicky");

    @JsonProperty("id")
    public final Integer ID;
    @JsonProperty("label")
    public final String LABEL;

    TipoSwitch(Integer id, String label) {
        this.ID = id;
        this.LABEL = label;
    }

    public static TipoSwitch valueOf(Integer id) {
        if (id == null) return null;
        for (TipoSwitch tipo : TipoSwitch.values()) {
            if (id.equals(tipo.ID))
                return tipo;
        }
        return null;
    }
}