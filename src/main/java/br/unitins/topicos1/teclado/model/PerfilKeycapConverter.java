package br.unitins.topicos1.teclado.model;

import br.unitins.topicos1.teclado.model.PerfilKeycap;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PerfilKeycapConverter implements AttributeConverter<PerfilKeycap, Integer> {

    @Override
    public Integer convertToDatabaseColumn(PerfilKeycap perfil) {
        return (perfil == null) ? null : perfil.ID;
    }

    @Override
    public PerfilKeycap convertToEntityAttribute(Integer id) {
        return PerfilKeycap.valueOf(id);
    }
}