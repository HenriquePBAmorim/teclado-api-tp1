package br.unitins.topicos1.teclado.model.jpa;

import br.unitins.topicos1.teclado.model.TipoTeclado;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoTecladoConverter implements AttributeConverter<TipoTeclado, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoTeclado tipo) {
        return (tipo == null) ? null : tipo.ID;
    }

    @Override
    public TipoTeclado convertToEntityAttribute(Integer id) {
        return TipoTeclado.valueOf(id);
    }
}