package br.unitins.topicos1.teclado.model.jpa;

import br.unitins.topicos1.teclado.model.TipoSwitch;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoSwitchConverter implements AttributeConverter<TipoSwitch, Integer> { 

    @Override
    public Integer convertToDatabaseColumn(TipoSwitch tipo) {
        return (tipo == null) ? null : tipo.ID;
    }

    @Override
    public TipoSwitch convertToEntityAttribute(Integer id) {
        return TipoSwitch.valueOf(id);
    }
}