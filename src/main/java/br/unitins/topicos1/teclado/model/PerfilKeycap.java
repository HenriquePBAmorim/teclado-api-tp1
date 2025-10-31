package br.unitins.topicos1.teclado.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum PerfilKeycap {
    OEM(1, "OEM"),
    CHERRY(2, "Cherry"),
    DSA(3, "DSA"),
    SA(4, "SA");

    @JsonProperty("id")
    public final Integer ID;
    @JsonProperty("label")
    public final String LABEL;

    PerfilKeycap(Integer id, String label) {
        this.ID = id;
        this.LABEL = label;
    }

    public static PerfilKeycap valueOf(Integer id) {
        if (id == null) return null;
        for (PerfilKeycap perfil : PerfilKeycap.values()) {
            if (id.equals(perfil.ID))
                return perfil;
        }
        return null;
    }
}