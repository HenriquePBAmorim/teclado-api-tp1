package br.unitins.topicos1.teclado.model.jpa;

import br.unitins.topicos1.teclado.model.TipoAcessorio; // 
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoAcessorioConverter implements AttributeConverter<TipoAcessorio, Integer> { 

    @Override
    public Integer convertToDatabaseColumn(TipoAcessorio tipo) {
        return (tipo == null) ? null : tipo.ID;
    }

    @Override
    public TipoAcessorio convertToEntityAttribute(Integer id) {
        return TipoAcessorio.valueOf(id);
    }
}